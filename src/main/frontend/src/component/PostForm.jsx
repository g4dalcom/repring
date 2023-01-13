import React, {useState} from "react";
import styled from "styled-components";
import {useMutation, useQueryClient} from "react-query";
import axios from "axios";
import header from "./Header";

const PostForm = () => {

    const [postTitle, setPostTitle] = useState("");
    const [postContent, setPostContent] = useState("");
    const queryClient = useQueryClient();

    const addPost = useMutation(
        () => axios.post("http://localhost:8080/api/posts", {
            title: postTitle,
            content: postContent
        }, header("application/json")))

    const onSubmitHandler = (event) => {
        event.preventDefault();
        console.log(postTitle, postContent);
        addPost.mutate(postTitle, postContent);
        queryClient.invalidateQueries("Posts");
        window.location.reload();
    }

    return (
        <div>
            <StForm onSubmit={onSubmitHandler}>
                <h1>게시글 작성</h1>
                <StElement>
                    <input type="text" value={postTitle} placeholder="제목을 입력해주세요."
                           onChange={(e) => setPostTitle(e.target.value)}/>
                </StElement>
                <StElement>
                    <textarea value={postContent} placeholder="내용은 300자 이내로 적어주세요."
                    onChange={(e) => setPostContent(e.target.value)}/>
                </StElement>
                <button type="submit">작성완료</button>
            </StForm>
        </div>
    )
}

export default PostForm;

const StForm = styled.form`
    display: flex;
    width: 100%;
    height: 100%;
    margin-top: 50px;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    & input {
        width: 350px;
    }
    
    & textarea {
        width: 350px;
        height: 320px;
    }
`;

const StElement = styled.div`
    margin: 0;
    padding: 10px;
`;
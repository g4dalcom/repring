import React, {useState} from "react";
import styled from "styled-components";

const PostForm = () => {
    const [postTitle, setPostTitle] = useState("");
    const [postContent, setPostContent] = useState("");

    const addPost = (e) => {
        e.defaultPrevented();
        console.log(postTitle, postContent);
    }

    return (
        <div>
            <StForm onSubmit={addPost}>
                <h1>게시글 작성</h1>
                <StElement>
                    <input type="text" value={postTitle} placeholder="제목을 입력해주세요."
                           onChange={e => setPostTitle(e.target.value)}/>
                </StElement>
                <StElement>
                    <textarea value={postContent} placeholder="내용은 300자 이내로 적어주세요."
                    onChange={e => setPostContent(e.target.value)}/>
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
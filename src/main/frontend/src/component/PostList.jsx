import styled from "styled-components";
import AddPost from "./AddPost";
import {useQuery, useMutation, useQueryClient} from "react-query";
import axios from "axios";

function PostList() {
    const queryClient = useQueryClient();
    const deletePost = useMutation((id) => {
        return axios.delete(`http://localhost:8080/api/posts/${id}`)
        },
        {
            onSuccess: () => {
                console.log("Mutate Success!");
                queryClient.invalidateQueries("Posts");
            }
        });


    const getPosts = async () => {
        const { data } = await axios.get("http://localhost:8080/api/posts");
        return data;
    };

    const {status, data, error} = useQuery("Posts", getPosts);

    if (status === "loading") console.log("loading..");
    if (status === "error") console.log("error!", error);
    if (status === "success") console.log("success!", data);

    const postCard = data && data.map((post) => {
        return (
            <StCard key={post.id}>
                <ul>
                    <li>{post.title}</li>
                    <li>{post.content}</li>
                </ul>
                <button value={post.id} onClick={() => deletePost.mutate(post.id)}>삭제하기</button>
            </StCard>
        )
    });

    return (
        <>
            <StBackGround>
                {postCard}
                <AddPost />
            </StBackGround>
        </>

    )
}

export default PostList;

const StCard = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    width: 200px;
    height: 350px;
    border: 1px solid black;
    border-radius: 5px;
    padding: 5px;
    margin: 200px 30px;
`;

const StBackGround = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
`;
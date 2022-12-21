import React, {useEffect, useState} from "react";
import axios from "axios";
import styled from "styled-components";
import AddCard from "./AddCard";

function Card() {

    const [posts, setPosts] = useState([]);

    const onDeleteHandler = async (id) => {
        await axios.delete(`http://localhost:8080/api/posts/${id}`);
        window.location.reload();
    };

    useEffect(() => {
        async function fetchData() {
            try {
                const response = await axios.get("http://localhost:8080/api/posts");
                setPosts(response.data);
            } catch (error) {
                console.log(error);
            }
        }
        fetchData();
    }, []);

    const postCard = posts.map(post => {
        return (
            <StCard key={post.id}>
                <ul>
                    <li>{post.title}</li>
                    <li>{post.content}</li>
                </ul>
                <button value={post.id} onClick={() => onDeleteHandler(post.id)}>삭제하기</button>
            </StCard>
        )
    });

    return (
        <>
            <StBackGround>
                {postCard}
                <AddCard />
            </StBackGround>
        </>

    )
}

export default Card;

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
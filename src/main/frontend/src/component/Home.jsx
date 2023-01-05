import React from "react";
import Header from "./Header";
import PostList from "./PostList";
import styled from "styled-components";

const Home = () => {
    return (
        <StHome>
            <Header />
            <PostList />
        </StHome>
    )
}

export default Home;

const StHome = styled.div`
    width: 100%;
    height: 100%;
    align-items: center;
    justify-content: center;
`;
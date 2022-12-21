import React from "react";
import Header from "./Header";
import Card from "./Card";
import styled from "styled-components";

const Home = () => {
    return (
        <StHome>
            <Header />
            <Card />
        </StHome>
    )
}

export default Home;

const StHome = styled.div`
    width: 100%;
    height: 300px;
    align-items: center;
    justify-content: center;
`;
import React from "react";
import Header from "./Header";
import styled from "styled-components";
import {Router} from "./Router";

const Home = () => {
    return (
        <StHome>
            <Header />
            <Router />
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
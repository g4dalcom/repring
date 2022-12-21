import React from "react";
import styled from "styled-components";
import {useNavigate} from "react-router-dom";

const Navigator = () => {

    const navigate = useNavigate();

    return (
        <StNavi>
            <StText>
                <StLogo onClick={() => {navigate("/")}}>Repring</StLogo>
                <StLoginBtn onClick={() => {navigate("/login")}}>로그인</StLoginBtn>
            </StText>
        </StNavi>
    )
}

export default Navigator;

const StNavi = styled.div`
    display: flex;
    width: 100%;
    height: 50px;
    background-color: #20232A;
`;

const StText = styled.h3`
    display: flex;
    width: 100%;
    justify-content: center;
    margin: auto 0;
    padding: 0;
    color: #61DAFB;
`;

const StLogo = styled.div`
    margin-right: auto;
    margin-left: 20px;
    cursor: pointer;
`;

const StLoginBtn = styled.div`
    margin-right: 20px;
    cursor: pointer;
`;
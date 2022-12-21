import React from "react";
import styled from "styled-components";

const NoticeBar = () => {
    return (
        <StNotice>
            <StText>공지가 오는 자리입니다.</StText>
        </StNotice>

    )
}

export default NoticeBar;

const StNotice = styled.div`
    display: flex;
    width: 100%;
    height: 30px;
    text-align: center;
    vertical-align: middle;
    color: white;
    background-color: #16181D;
    margin: auto 0;
    padding: 0px;
    align-items: center;
    justify-content: center;
`;

const StText = styled.h4`
    vertical-align: middle;
    margin: auto 0;
    padding: 0px;
`;
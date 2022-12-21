import React from "react";
import styled from "styled-components";
import NoticeBar from "./NoticeBar";
import Navigator from "./Navigator";

const Header = () => {

    return (
        <StHeader>
            <NoticeBar />
            <Navigator />
        </StHeader>
    );
};

export default Header;

const StHeader = styled.div`
    position: fixed;
    width: 100%;
    height: 80px;
    `;
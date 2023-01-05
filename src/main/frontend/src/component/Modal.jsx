import styled from "styled-components";
import PostForm from "./PostForm";

const Modal = ({setModalUp}) => {

    const closeModal = () => {
        setModalUp(false);
    }

    return (
        <StContainer>
            <StButton onClick={closeModal}>X</StButton>
            <PostForm />
        </StContainer>
    )
}

export default Modal;

const StContainer = styled.div`
    width: 500px;
    height: 600px;
    z-index: 999;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: white;
    border: 2px solid black;
    border-radius: 10px;
`;

const StButton = styled.button`
    position: absolute;
    right: 10px;
    top: 10px;
`;
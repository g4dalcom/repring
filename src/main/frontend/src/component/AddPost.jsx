import React, { useState } from "react";
import Modal from "./Modal";
import styled from "styled-components";

const AddPost = () => {
  const [modalUp, setModalUp] = useState(false);

  const showModal = () => {
    setModalUp(true);
  };

  return (
    <StEmptyCard>
      <button onClick={showModal}>추가하기</button>
      {modalUp && <Modal setModalUp={setModalUp} />}
    </StEmptyCard>
  );
};

export default AddPost;

const StEmptyCard = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100px;
  height: 100px;
  border: 3px solid purple;
  border-radius: 10px;
  padding: 10px;
`;

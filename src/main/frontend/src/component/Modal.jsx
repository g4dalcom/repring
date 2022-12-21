const Modal = ({setModalUp}) => {

    const closeModal = () => {
        setModalUp(false);
    }

    return (
        <div>
            <button onClick={closeModal}>X</button>
        </div>
    )
}

export default Modal;
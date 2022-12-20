import './App.css';
import React, {useEffect, useState} from "react";
import axios from "axios";

function App() {
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

    return (
        <div>
            <ul>
                {posts.map(post => (
                    <li key={post.id}>
                        <div>{post.title}</div>
                        <div>{post.content}</div>
                        <button value={post.id} onClick={() => onDeleteHandler(post.id)}>삭제하기</button>
                    </li>
                ))}
            </ul>
        </div>
    )

}
export default App;

import React, {useState, useEffect} from "react";
import axios from "axios";

const Api = () => {

    const [posts, setPosts] = useState([]);

    useEffect(() => {
        axios({
            method: "GET",
            url: "http://localhost:8080/api/posts"
        }).then(res => setPosts(res.data))
    })

    return (
        <ul>
            {posts.map(post => (
                <li key={post.id}>{post.title}</li>
            ))}
        </ul>
    )
}

export default Api;
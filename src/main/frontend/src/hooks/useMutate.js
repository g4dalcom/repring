import axios from "axios";
import {useMutation, useQueryClient} from "react-query";

const fetching = (props) => {
    return axios
        .post(
            "http://localhost:8080/api/posts",
            JSON.stringify({
                title: props.title,
                content: props.content,
            }),
            {
                headers: {"Content-type": "application/json;charset=UTF-8"},
            }
        )
        .then(console.log);
}

export const useMutate = (props) => {
    const queryClient = useQueryClient();

    return useMutation(
        (data) => {
            fetching({
                title: data.title,
                content: data.content,
            });
        },
        {
            onSuccess: () => {
                queryClient.invalidateQueries("Posts")
            }
        }
    )
}
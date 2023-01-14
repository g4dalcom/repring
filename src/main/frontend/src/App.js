import './App.css';
import Home from "./component/Home";
import {QueryClient, QueryClientProvider, QueryCache} from "react-query";
import React from "react";

function App() {

    const queryClient = new QueryClient({
        queryCache: new QueryCache({
            onError: (error, query) => {
                console.log("onError", error);
            },
            onSuccess: (data) => {
                console.log("onSuccess", data);
            }
        })
    })

    return (
        <QueryClientProvider client={queryClient}>
            <Home />
        </QueryClientProvider>
    )

}
export default App;

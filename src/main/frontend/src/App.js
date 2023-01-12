import './App.css';
import Home from "./component/Home";
import {BrowserRouter} from "react-router-dom";
import {QueryClient, QueryClientProvider, QueryCache} from "react-query";

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
            <BrowserRouter>
                <Home />
            </BrowserRouter>
        </QueryClientProvider>
    )

}
export default App;

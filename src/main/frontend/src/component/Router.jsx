import {Route, Routes} from "react-router-dom";
import PostList from "./PostList";
import LoginForm from "./LoginForm";
import RegisterForm from "./RegisterForm";
import NotFoundPage from "./pages/NotFoundPage";
import React from "react";

export const Router = () => {
    return (
        <Routes>
            <Route path="/" element={<PostList />}></Route>
            <Route path="/login" element={<LoginForm />}></Route>
            <Route path="/register" element={<RegisterForm />}></Route>
            <Route path="*" element={<NotFoundPage />}></Route>
        </Routes>
    )
}
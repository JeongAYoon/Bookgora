import React, {useState} from 'react';
import axios from "axios";

function Login() {
    const [inputId, setInputId] = useState("");
    const [inputPw, setInputPw] = useState("");

    const idRegEx = /^[A-Za-z0-9]{2,12}$/;
    const passwordRegEx = /^[A-Za-z0-9]{8,20}$/;

    const handleInputId = (e) => {
        setInputId(e.target.value);
    }

    const handleInputPw = (e) => {
        setInputPw(e.target.value);
    }

    const onClickLogin = () => {
        if(idRegEx.test(inputId) && passwordRegEx.test(inputPw)) {
            axios
                .post("/api/login", {
                    username: inputId,
                    password: inputPw,
                })
                .then((response) => {
                    if(response.data) {
                        window.location.href = '/';
                        sessionStorage.setItem("user", JSON.stringify(response.data));
                    }
                })
                .catch((error) => {
                    alert("아이디 혹은 패스워드를 잘못 입력하였습니다.");
                });
        } else {
            alert("형식이 올바르지 않습니다.");
        }
    }

    return (
        <div className="login template d-flex justify-content-center align-items-center 100-w vh-100">
            <div className="40-w p-5 rounded bg-white shadow">
                <form>
                    <h3>Sign In</h3>
                    <div className="mb-2">
                        <label htmlFor="username">아이디</label>
                        <input type="text" placeholder="아이디를 입력하세요." className="form-control" value={inputId} onChange={handleInputId} />
                    </div>
                    <div className="mb-2">
                        <label htmlFor="password">비밀번호</label>
                        <input type="password" placeholder="비밀번호를 입력하세요." className="form-control" value={inputPw} onChange={handleInputPw} />
                    </div>
                    <div className="d-grid">
                        <button className="btn btn-primary" onClick={event => {
                            event.preventDefault();
                            onClickLogin();
                        }}>로그인</button>
                    </div>
                    <p className="text-right mt-4">
                        아직 가입을 안 하셨나요? <a href="/signup">회원가입</a>
                    </p>
                </form>
            </div>
        </div>
    );
}

export default Login;
import React, {useState} from 'react';
import axios from "axios";

function Signup() {
    const [inputId, setInputId] = useState("");
    const [inputPw1, setInputPw1] = useState("");
    const [inputPw2, setInputPw2] = useState("");
    const [inputEmail, setInputEmail] = useState("");

    const handleInputId = (e) => {
        setInputId(e.target.value);
    }

    const handleInputPw1 = (e) => {
        setInputPw1(e.target.value);
    }

    const handleInputPw2 = (e) => {
        setInputPw2(e.target.value);
    }

    const handleEmail = (e) => {
        setInputEmail(e.target.value);
    }

    const idRegEx = /^[A-Za-z0-9]{2,12}$/;
    const emailRegEx = /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\.[A-Za-z]{2,3}$/;
    const passwordRegEx = /^[A-Za-z0-9]{8,20}$/;

    const onClickSignup = () => {
        if(idRegEx.test(inputId) && emailRegEx.test(inputEmail) && passwordRegEx.test(inputPw1) && (inputPw1 === inputPw2)) {
            axios
                .post("/api/signup", {
                    username: inputId,
                    password1: inputPw1,
                    password2: inputPw2,
                    email: inputEmail,
                })
                .then((response) => {
                    if(response.data) {
                        alert("회원가입에 성공하였습니다.")
                        window.location.href = '/';
                        sessionStorage.setItem("user", JSON.stringify(response.data));
                    } else {
                        alert("중복되는 아이디 혹은 이메일이 있습니다.");
                    }
                })
                .catch();
        } else {
            alert("형식이 올바르지 않거나 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }
    };

    return (
        <div className="signup template d-flex justify-content-center align-items-center 100-w vh-100">
            <div className="40-w p-5 rounded bg-white shadow">
                <form>
                    <h3>Sign Up</h3>
                    <div className="mb-2">
                        <label htmlFor="username" className="block">아이디</label>
                        <label>(아이디는 영문과 숫자만 가능하며 2자 이상 12자 이하여야 합니다.)</label>
                        <input type="text"  placeholder="아이디를 입력하세요." className="form-control" value={inputId} onChange={handleInputId} />
                    </div>
                    <div className="mb-2">
                        <label htmlFor="password" className="block">비밀번호</label>
                        <label>(비밀번호는 영문 혹은 숫자를 혼합하여 8자 이상 20자 이하여야 합니다.)</label>
                        <input type="password" placeholder="비밀번호를 입력하세요." className="form-control" value={inputPw1} onChange={handleInputPw1} />
                    </div>
                    <div className="mb-2">
                        <label htmlFor="password">비밀번호 확인</label>
                        <input type="password" placeholder="비밀번호를 다시 입력하세요." className="form-control" value={inputPw2} onChange={handleInputPw2} />
                    </div>
                    <div className="mb-2">
                        <label htmlFor="password">이메일</label>
                        <input type="email" placeholder="이메일을 입력하세요." className="form-control" value={inputEmail} onChange={handleEmail} />
                    </div>
                    <div className="d-grid">
                        <button className="btn btn-primary" onClick={event => {
                            event.preventDefault();
                            onClickSignup();
                        }}>회원가입</button>
                    </div>
                    <p className="text-right mt-4">
                        이미 가입하셨나요? <a href="/login">로그인</a>
                    </p>
                </form>
            </div>
        </div>
    );
}

export default Signup;
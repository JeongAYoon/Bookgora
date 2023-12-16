import React from 'react';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import logo from '../assets/logo.png';

function SiteNav() {
    const isLoggedIn = JSON.parse(sessionStorage.getItem("user")) !== null;
    let username = '';

    const handleLogout = () => {
        sessionStorage.removeItem("user");
        window.location.href = '/';
    }

    if(isLoggedIn) {
        username = JSON.parse(sessionStorage.getItem("user")).username;
    }

    return (
        <Navbar collapseOnSelect expand="lg" className="bg-body-secondary">
            <Container>
                <Navbar.Brand href="/">
                    <img className="w-50" src={logo} />
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link href="/">홈</Nav.Link>
                        <Nav.Link href="#">토론방 검색</Nav.Link>
                        <Nav.Link href="/room/create">토론방 생성</Nav.Link>
                    </Nav>
                    <Nav>
                        {isLoggedIn ? (
                            <>
                                <Nav.Item className="mx-6 my-2">{username}님, 안녕하세요!</Nav.Item>
                                <Nav.Link onClick={handleLogout}>로그아웃</Nav.Link>
                            </>
                        ) : (
                            <>
                                <Nav.Link href="/login">로그인</Nav.Link>
                                <Nav.Link eventKey={2} href="/signup">
                                    회원가입
                                </Nav.Link>
                            </>
                        )}
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default SiteNav;
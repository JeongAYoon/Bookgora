import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from 'axios';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Modal from "react-bootstrap/Modal";

function CreateRoom() {
    let navigate = useNavigate();
    let username = '';

    const isLoggedIn = JSON.parse(sessionStorage.getItem("user")) !== null;
    if(isLoggedIn) {
        username = JSON.parse(sessionStorage.getItem("user")).username;
    }

    const [show, setShow] = useState(false);
    const [inputSearch, setInputSearch] = useState("");
    const [searchResults, setSearchResults] = useState([]);

    const [inputSubject, setInputSubject] = useState("");
    const [inputBody, setInputBody] = useState("");

    const [title, setTitle] = useState("");
    const [author, setAuthor] = useState("");
    const [publisher, setPublisher] = useState("");
    const [image, setImage] = useState("");
    const [pubdate, setPubdate] = useState("");
    const [isbn, setIsbn] = useState("");

    const handleClose = () => {
        setShow(false);
        setInputSearch('');
        setSearchResults([]);
    };

    const handleShow = () => setShow(true);

    const handleInputSubject = (e) => {
        setInputSubject(e.target.value);
    }

    const handleInputBody = (e) => {
        setInputBody(e.target.value);
    }

    const handleInputSearch = (e) => {
        setInputSearch(e.target.value);
    };

    const onClickSearch = () => {
        if (inputSearch.length > 0) {
            axios
                .post("/api/book/search", {
                    text: inputSearch,
                })
                .then((response) => {
                    setSearchResults(response.data);
                })
                .catch();
        } else {
            alert("검색어는 최소 1자 이상 입력하셔야 됩니다.");
        }
    };

    const onClickSubmit = () => {
        if(isLoggedIn) {
            if(inputSubject.length > 0 && inputBody.length > 0 && title.length > 0) {
                // 책 있는지 조회 후 없을 시 등록

                axios
                    .post("/api/book/create", {
                        title: title,
                        author: author,
                        publisher: publisher,
                        image: image,
                        pubdate: pubdate,
                        isbn: isbn,
                    })
                    .then((response) => {
                        axios
                            .post("/api/room/create", {
                                subject: inputSubject,
                                isbn: isbn,
                                body: inputBody,
                                username: username,
                            })
                            .then((response) => {
                                console.log(response.data);
                            })
                            .catch();
                    })
                    .catch();
            } else {
                alert("폼을 모두 채워주세요.");
            }
        } else {
            alert("글을 등록하려면 로그인을 해야 합니다.");
            navigate("/login");
        }
    };

    return (
        <Container>
            <Form>
                <Form.Group className="mt-3 mb-3" controlId="subject">
                    <Form.Label>주제</Form.Label>
                    <Form.Control type="text" placeholder="주제를 입력하세요." maxLength={32} value={inputSubject} onChange={handleInputSubject} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="book">
                    <Row>
                        <Form.Label>책</Form.Label>
                        <Col>
                            <Form.Control className="w-1/3" placeholder={title === "" ? "원하는 책을 검색하세요." : title + " (" + author + ") 출판사 : " + publisher + " " + pubdate} disabled />
                        </Col>
                        <Col>
                            <Button variant="secondary" type="button" onClick={handleShow}>
                                책 검색하기
                            </Button>
                            <Modal show={show} onHide={handleClose}>
                                <Modal.Header closeButton>
                                    <Modal.Title>책 검색하기</Modal.Title>
                                </Modal.Header>
                                <Modal.Body style={{ maxHeight: '400px', overflowY: 'auto' }}>
                                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                                        <Row>
                                            <Col xs={10}>
                                                <Form.Control
                                                    type="text"
                                                    placeholder="책 제목을 입력하세요."
                                                    value={inputSearch}
                                                    onChange={handleInputSearch}
                                                    autoFocus
                                                />
                                            </Col>
                                            <Col>
                                                <Button variant="primary" type="button" onClick={onClickSearch}>
                                                    검색
                                                </Button>
                                            </Col>
                                        </Row>
                                    </Form.Group>
                                    {searchResults.length > 0 && (
                                        <div>
                                            <h5>검색 결과:</h5>
                                            <ul style={{ listStyleType: 'none', padding: 0 }}>
                                                {searchResults.map((result, index) => {
                                                    const year = result.pubdate.substring(0, 4);
                                                    const month = result.pubdate.substring(4, 6);
                                                    const day = result.pubdate.substring(6, 8);

                                                    const formattedDate = `${year}년 ${month}월 ${day}일`;

                                                    return (
                                                        <li key={index} style={{ marginBottom: '20px', borderBottom: '1px solid #ccc', paddingBottom: '10px' }} className="flex relative">
                                                            <img src={result.image} alt="Book Cover" style={{ maxWidth: '100px', maxHeight: '150px' }} className="flex-initial"/>
                                                            <div className="flex-initial mx-4">
                                                                <h5 className="mb-1">{result.title}</h5>
                                                                <p className="mb-1">작가: {result.author}</p>
                                                                <p className="mb-1">출판사: {result.publisher}</p>
                                                                <p className="mb-1">출판일: {formattedDate}</p>
                                                            </div>
                                                            <Button variant="success" type="button" className="absolute right-[20px] top-[100px]" onClick={() => {
                                                                setTitle(result.title);
                                                                setAuthor(result.author);
                                                                setPublisher(result.publisher);
                                                                setImage(result.image);
                                                                setPubdate(result.pubdate);
                                                                setIsbn(result.isbn);
                                                                handleClose();
                                                            }}>선택</Button>
                                                        </li>
                                                    );
                                                })}
                                            </ul>
                                        </div>
                                    )}
                                </Modal.Body>
                                <Modal.Footer>
                                    <Button variant="secondary" onClick={handleClose}>
                                        닫기
                                    </Button>
                                </Modal.Footer>
                            </Modal>
                        </Col>
                    </Row>
                </Form.Group>
                <Form.Group className="mb-3" controlId="body">
                    <Form.Label>내용</Form.Label>
                    <Form.Control as="textarea" rows={18} placeholder="내용을 입력하세요. (토론 주제를 간략하게 포함하는 내용을 작성해야 합니다.)" value={inputBody} onChange={handleInputBody}/>
                </Form.Group>
                <Button variant="primary" className="float-right" type="submit" onClick={event => {
                    event.preventDefault();
                    onClickSubmit();
                }}>등록</Button>
            </Form>
        </Container>
    );
}

export default CreateRoom;
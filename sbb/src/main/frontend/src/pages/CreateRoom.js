import React, {useState} from "react";
import axios from 'axios';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Modal from "react-bootstrap/Modal";

function CreateRoom() {
    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    return (
        <Container>
            <Form>
                <Form.Group className="mt-3 mb-3" controlId="subject">
                    <Form.Label>주제</Form.Label>
                    <Form.Control type="text" placeholder="주제를 입력하세요." />
                </Form.Group>
                <Form.Group className="mb-3" controlId="book">
                    <Row>
                        <Form.Label>책</Form.Label>
                        <Col>
                            <Form.Control className="w-1/3" placeholder="원하는 책을 검색하세요." disabled />
                        </Col>
                        <Col>
                            <Button variant="secondary" type="button" onClick={handleShow}>
                                책 검색하기
                            </Button>
                            <>
                                <Modal show={show} onHide={handleClose}>
                                    <Modal.Header closeButton>
                                        <Modal.Title>책 검색하기</Modal.Title>
                                    </Modal.Header>
                                    <Modal.Body>
                                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                                            <Row>
                                                <Col xs={10}>
                                                    <Form.Control
                                                        type="text"
                                                        placeholder="책 제목을 입력하세요."
                                                        autoFocus
                                                    />
                                                </Col>
                                                <Col>
                                                    <Button variant="primary" type="button">
                                                        검색
                                                    </Button>
                                                </Col>
                                            </Row>
                                        </Form.Group>
                                    </Modal.Body>
                                    <Modal.Footer>
                                        <Button variant="secondary" onClick={handleClose}>
                                            닫기
                                        </Button>
                                    </Modal.Footer>
                                </Modal>
                            </>
                        </Col>
                    </Row>
                </Form.Group>
                <Form.Group className="mb-3" controlId="body">
                    <Form.Label>내용</Form.Label>
                    <Form.Control as="textarea" rows={18} placeholder="내용을 입력하세요. (토론 주제를 간략하게 포함하는 내용을 작성해야 합니다.)" />
                </Form.Group>
                <Button variant="primary" className="float-right" type="submit">등록</Button>
            </Form>
        </Container>
    );
}

export default CreateRoom;
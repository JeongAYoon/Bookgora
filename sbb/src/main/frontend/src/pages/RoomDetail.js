import { React } from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';

function RoomDetail() {
    return (
        <div className="detail">
            <div className="bg-zinc-800 flex flex-row">
                <img className="w-[270px] h-[270px] shadow flex-col mx-8 my-8" src="https://via.placeholder.com/326x326" />
                <div className="flex-col">
                    <h2 className="text-white mx-8 py-12">요조에게 필요한건 무엇일까?</h2>
                    <h5 className="text-white mx-8">인간실격(다자이 오사무) 출판사:민음사</h5>
                    <div className="text-white mx-8">생성자 : ryujunghwan</div>
                    <div className="text-white mx-8">12/16/2023, 3:35:48 PM</div>
                    <Button className="mx-8 my-3" variant="primary">방 참가하기</Button>
                </div>
            </div>
            <Card className="mx-8 my-8">
                <Card.Body>
                    <Card.Title className="border-bottom">방 설명</Card.Title>
                    <Card.Text>Test</Card.Text>
                </Card.Body>
            </Card>
        </div>
    );
}

export default RoomDetail;
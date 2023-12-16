import { React, useEffect, useState } from 'react';
import axios from 'axios';
import Button from 'react-bootstrap/Button';
import { useNavigate } from "react-router-dom";

function List() {
    const [roomList, setRoomList] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        // Fetch room list from the API
        axios.get('/api/room/list')
            .then(response => {
                setRoomList(response.data.content);
            })
            .catch();
    }, []);

    const truncateText = (text, maxLength) => {
        if (text.length <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength) + '...';
    };

    const onClickParticipate = (no) => {
        navigate(`/room/detail/${no}`);
    }

    return (
        <div className="list xl:grid grid-cols-3 gap-3 justify-items-center block">
            {roomList.map((room, index) => (
                <div key={index} className="w-[385px] h-[180px] mx-9 my-9 shadow flex" key={index}>
                    <img className="w-[145px] h-[180px]" src={room.book.image} alt="Book Cover" />
                    <div className="w-[240px] h-[180px] flex-row px-3 py-3">
                        <h5 className="flex flex-col mb-1">{truncateText(room.subject, 20)}</h5>
                        <p className="mb-1 text-sm">{truncateText(room.book.name, 10)}</p>
                        <p className="relative text-xs mb-1">{truncateText(room.book.author, 10)}</p>
                        <p className="relative text-xs mb-1">생성자: {room.creator.username}</p>
                        <p className="relative text-xs mb-1">{new Date(room.createDate).toLocaleString()}</p>
                        <Button variant="primary" type="button" size="sm" className="relative left-36 bottom-4" onClick={() => {
                            onClickParticipate(room.id);
                        }}>
                            토론하기
                        </Button>
                    </div>
                </div>
            ))}
        </div>
    );
}

export default List;
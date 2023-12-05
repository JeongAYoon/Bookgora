import image from '../assets/istockphoto-1438537474-612x612.jpg';
import List from '../components/List';

function Main() {
    return (
        <div>
            <section className="relative py-24 px-4 flex justify-between items-center bg-blue-950">
                <div className="z-20 relative text-white container mx-auto flex flex-col lg:flex-row lg:items-center lg:w-1/2">
                    <div className="text-center lg:text-left lg:w-2/3">
                        <h1 className="mb-4 font-bold text-3xl drop-shadow-md">이제는 독서 토론도 온라인으로 언제, 어디서나</h1>
                        <p className="leading-normal text-xl mb-6 drop-shadow-md">책을 읽다가 토론하고 싶은 주제가 생겼다면?</p>
                        <a href="#" className="inline-block w-[130px] h-[48px] text-white bg-gradient-to-br from-green-400 to-blue-600 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-green-200 dark:focus:ring-green-800 font-medium rounded-lg px-4 py-2.5 text-center text-xl text-decoration-none drop-shadow-md lg:ml-96">체험하기</a>
                    </div>
                    <div className="lg:w-1/3 flex justify-center lg:justify-end">
                        <img className="w-[350px] h-[220px] rounded-full lg:ml-6" src={image} alt="이미지" />
                    </div>
                </div>
            </section>
            <List />
        </div>
    );
}

export default Main;

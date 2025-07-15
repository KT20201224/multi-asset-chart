import FloatingIcon from "../components/FloatingIcon";
import { iconList } from "../constants/iconList";
import { useNavigate } from "react-router-dom";

export default function Start() {
  const navigate = useNavigate();

  return (
    <div
      className="w-screen h-screen bg-cover relative overflow-hidden"
      style={{ backgroundImage: "url('/background.png')" }}
    >
      {/* 🔄 떠다니는 아이콘들 */}
      {iconList.map((src, i) => (
        <FloatingIcon key={i} src={src} />
      ))}

      {/* 중앙 텍스트 */}
      <div className="z-10 absolute inset-0 flex flex-col items-center justify-center text-white text-center">
        {/* 페이지 제목 */}
        <h1 className="text-4xl font-bold mb-4">Multi Asset Chart</h1>
        {/* 페이지 부제목 */}
        <p className="text-lg text-gray-300 mb-6">
          자산 차트를 자유롭게 구성하고 관리하세요
        </p>
        <button
          onClick={() => navigate("/main")}
          className="bg-gradient-to-r from-purple-500 to-blue-500 text-white px-6 py-3 rounded-full text-lg shadow-md hover:scale-105 transition"
        >
          시작하기
        </button>
      </div>
    </div>
  );
}

// src/components/SearchBar.tsx
import { useState } from "react";

interface Props {
  onSearch: (type: string, symbol: string) => void;
}

export default function SearchBar({ onSearch }: Props) {
  const [type, setType] = useState("stock");
  const [symbol, setSymbol] = useState("");

  const handleSearch = () => {
    if (!symbol) return;
    onSearch(type, symbol);
    setSymbol(""); // 입력 초기화
  };

  return (
    <div className="flex items-center gap-2 bg-gray-800 p-4 rounded-lg shadow-md">
      <select
        className="bg-gray-700 text-white p-2 rounded"
        value={type}
        onChange={(e) => setType(e.target.value)}
      >
        <option value="stock">주식</option>
        <option value="crypto">코인</option>
        <option value="forex">환율</option>
      </select>

      <input
        type="text"
        className="bg-gray-700 text-white p-2 rounded w-48"
        placeholder="심볼 입력 (예: AAPL)"
        value={symbol}
        onChange={(e) => setSymbol(e.target.value)}
      />

      <button
        onClick={handleSearch}
        className="bg-blue-600 hover:bg-blue-700 px-4 py-2 rounded text-white"
      >
        검색
      </button>
    </div>
  );
}

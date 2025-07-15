import { useState } from "react";
import Header from "../components/Header";
import SearchBar from "../components/SearchBar";
import ChartGrid from "../components/ChartGrid";

interface Asset {
  id: number;
  type: string;
  symbol: string;
}

export default function Main() {
  const [assets, setAssets] = useState<Asset[]>([]);
  const [nextId, setNextId] = useState(1);

  const handleSearch = (type: string, symbol: string) => {
    const exists = assets.some((a) => a.type === type && a.symbol === symbol);
    if (exists) return;
    setAssets([...assets, { id: nextId, type, symbol }]);
    setNextId(nextId + 1);
  };

  const handleRemove = (id: number) => {
    setAssets(assets.filter((a) => a.id !== id));
  };

  return (
    <div className="w-screen min-h-screen bg-black text-white relative">
      <Header />
      <div className="absolute top-20 left-1/2 transform -translate-x-1/2 z-10">
        <SearchBar onSearch={handleSearch} />
      </div>
      <main className="pt-32 px-6 pb-24">
        <ChartGrid assets={assets} onRemove={handleRemove} />
      </main>
    </div>
  );
}

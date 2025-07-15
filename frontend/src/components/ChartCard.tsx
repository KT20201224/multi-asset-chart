// src/components/ChartCard.tsx
import { X } from "lucide-react";
import CandleChart from "./CandleChart";
import { Bitcoin, LineChart, DollarSign } from "lucide-react";

interface Props {
  id: number;
  type: string;
  symbol: string;
  onRemove: (id: number) => void;
}

const typeIconMap: Record<string, React.ReactNode> = {
  crypto: (
    <div className="w-6 h-6 rounded-full bg-orange-500 flex items-center justify-center">
      <Bitcoin className="w-3.5 h-3.5 text-white" />
    </div>
  ),
  stock: (
    <div className="w-6 h-6 rounded-full bg-blue-500 flex items-center justify-center">
      <LineChart className="w-3.5 h-3.5 text-white" />
    </div>
  ),
  forex: (
    <div className="w-6 h-6 rounded-full bg-green-500 flex items-center justify-center">
      <DollarSign className="w-3.5 h-3.5 text-white" />
    </div>
  ),
};

export default function ChartCard({ id, type, symbol, onRemove }: Props) {
  return (
    <div className="bg-gray-900 rounded-lg shadow-md relative overflow-hidden">
      {/* 헤더 */}
      <div className="relative h-12 flex items-center justify-center border-b border-gray-800 px-4">
        <div className="absolute bottom-0 left-0 w-full h-px bg-gradient-to-r from-transparent via-gray-600 to-transparent" />
        <div className="flex items-center gap-2 pointer-events-none">
          {typeIconMap[type]}
          <h2 className="text-md font-bold text-orange-300">
            {symbol.toUpperCase()}
          </h2>
        </div>
        <button
          onClick={() => onRemove(id)}
          className="absolute top-1/2 right-2 -translate-y-1/2 p-1 hover:bg-gray-700 rounded"
        >
          <X size={18} className="text-gray-400 hover:text-red-500" />
        </button>
      </div>

      {/* 차트 */}
      <div className="relative bg-gray-900 rounded shadow p-4 h-[300px] overflow-hidden">
        <CandleChart type={type} symbol={symbol} />
      </div>
    </div>
  );
}

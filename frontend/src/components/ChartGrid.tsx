// src/components/ChartGrid.tsx
import ChartCard from "./ChartCard";

interface Asset {
  id: number;
  type: string;
  symbol: string;
}

interface Props {
  assets: Asset[];
  onRemove: (id: number) => void;
}

export default function ChartGrid({ assets, onRemove }: Props) {
  return (
    <div className="min-h-[600px] w-full bg-neutral-900 rounded-lg p-6">
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 pr-4 pb-4">
        {assets.map((asset) => (
          <ChartCard
            key={asset.id}
            id={asset.id}
            type={asset.type}
            symbol={asset.symbol}
            onRemove={onRemove}
          />
        ))}
      </div>
    </div>
  );
}

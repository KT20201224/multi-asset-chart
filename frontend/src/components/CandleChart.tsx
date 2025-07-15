// src/components/CandleChart.tsx
import { useEffect, useRef } from "react";
import { createChart } from "lightweight-charts";
import type { IChartApi, Time } from "lightweight-charts";

interface Props {
  type: string;
  symbol: string;
}

interface CandleAPIResponse {
  date: number;
  open: number;
  high: number;
  low: number;
  close: number;
}

export default function CandleChart({ type, symbol }: Props) {
  const containerRef = useRef<HTMLDivElement>(null);
  const chartRef = useRef<IChartApi | null>(null);

  useEffect(() => {
    if (!containerRef.current) return;

    const chart = createChart(containerRef.current, {
      layout: {
        background: { color: "#000000" },
        textColor: "#d1d4dc",
      },
      grid: {
        vertLines: { color: "#2a2a2a" },
        horzLines: { color: "#2a2a2a" },
      },
      width: containerRef.current.clientWidth,
      height: containerRef.current.clientHeight,
    });

    chartRef.current = chart;
    const series = chart.addCandlestickSeries();

    fetch(
      `http://localhost:8080/api/v1/chart/candle?type=${type}&symbol=${symbol}`
    )
      .then((res) => res.json())
      .then((data) => {
        const candles = data.candles.map((item: CandleAPIResponse) => ({
          time: item.date as Time,
          open: item.open,
          high: item.high,
          low: item.low,
          close: item.close,
        }));

        series.setData(candles);
        chart.timeScale().fitContent();
      });

    // 리사이즈 처리
    const observer = new ResizeObserver((entries) => {
      if (!entries[0]) return;
      const { width, height } = entries[0].contentRect;
      chart.resize(width, height);
    });

    observer.observe(containerRef.current);

    return () => {
      if (chartRef.current) {
        chartRef.current.remove();
        chartRef.current = null;
      }
    };
  }, [type, symbol]);

  return <div ref={containerRef} style={{ width: "100%", height: "100%" }} />;
}

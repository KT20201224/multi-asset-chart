import { motion } from "framer-motion";
import { useState } from "react";

interface Props {
  src: string;
}

export default function FloatingIcon({ src }: Props) {
  const [position] = useState(() => ({
    x: Math.random() * window.innerWidth,
    y: Math.random() * window.innerHeight,
  }));

  return (
    <motion.img
      src={src}
      alt="icon"
      className="w-10 h-10 absolute opacity-30"
      initial={{ x: position.x, y: position.y }}
      animate={{
        y: position.y + 20,
        x: position.x + 10,
      }}
      transition={{
        repeat: Infinity,
        repeatType: "reverse",
        duration: 4 + Math.random() * 2,
        ease: "easeInOut",
      }}
    />
  );
}

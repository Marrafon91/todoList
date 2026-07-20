import { Search } from 'lucide-react';
import './style.css';

type Props = {
  value: string;
  onChange: (value: string) => void;
};

export default function SearchBar({ value, onChange }: Props) {
  return (
    <div className="search-bar">
      <Search size={18} />
      <input
        type="text"
        placeholder="Buscar tarefas..."
        value={value}
        onChange={(e) => onChange(e.target.value)}
      />
    </div>
  );
}

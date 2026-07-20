import { Search } from 'lucide-react';
import './style.css';

export default function SearchBar() {
  return (
    <div className="search-bar">
      <Search size={18} />

      <input type="text" placeholder="Buscar tarefas..." />
    </div>
  );
}

import './style.css';
import { ListTodo } from 'lucide-react';

export default function SidebarHeader() {
  return (
    <div className="sidebar-header">
      <div className="sidebar-header-icon">
        <ListTodo size={20} />
      </div>

      <div>
        <h2>Tarefas</h2>
        <p>Organize seu dia</p>
      </div>
    </div>
  );
}

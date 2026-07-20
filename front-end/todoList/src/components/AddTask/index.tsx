import { Plus } from 'lucide-react';
import './style.css';

export default function AddTask() {
  return (
    <div className="add-task">
      <button className="add-task-button">
        <Plus size={22} />
      </button>
      <span>Adicionar uma nova tarefa...</span>
    </div>
  );
}

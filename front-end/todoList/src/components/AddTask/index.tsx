import { Plus } from 'lucide-react';
import './style.css';

type Props = {
  onClick: () => void;
};

export default function AddTask({ onClick }: Props) {
  return (
    <div className="add-task" onClick={onClick}>
      <button className="add-task-button">
        <Plus size={22} />
      </button>
      <span>Adicionar uma nova tarefa...</span>
    </div>
  );
}

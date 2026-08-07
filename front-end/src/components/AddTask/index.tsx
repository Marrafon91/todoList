import { Plus } from 'lucide-react';
import './style.css';

type Props = {
  onClick: () => void;
};

export default function AddTask({ onClick }: Props) {
  return (
    <button className="add-task" onClick={onClick}>
      <span className="add-task-button">
        <Plus size={22} />
      </span>

      <span>Adicionar uma nova tarefa...</span>
    </button>
  );
}

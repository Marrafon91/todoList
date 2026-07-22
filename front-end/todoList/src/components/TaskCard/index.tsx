import './style.css';

import {
  Circle,
  CheckCircle2,
  Calendar,
  Flag,
  Pencil,
  Trash2,
} from 'lucide-react';

import type { TaskDTO } from '../../models/task';

type Props = {
  task: TaskDTO;
  onToggleDone: (task: TaskDTO) => void;
  onEdit: (task: TaskDTO) => void;
  onDelete: (id: number) => void;
};

export default function TaskCard({
  task,
  onToggleDone,
  onEdit,
  onDelete,
}: Props) {
  function priorityLabel() {
    switch (task.priority) {
      case 'HIGH':
        return 'Alta';

      case 'MEDIUM':
        return 'Média';

      default:
        return 'Baixa';
    }
  }

  function priorityClass() {
    switch (task.priority) {
      case 'HIGH':
        return 'high';

      case 'MEDIUM':
        return 'medium';

      default:
        return 'low';
    }
  }

  const dueDate = new Date(task.dueDate).toLocaleDateString('pt-BR', {
    day: '2-digit',
    month: 'short',
  });

  return (
    <div className={`task-card ${task.done ? 'completed' : ''}`}>
      <button className="task-check" onClick={() => onToggleDone(task)}>
        {task.done ? <CheckCircle2 size={22} /> : <Circle size={22} />}
      </button>

      <div className="task-content">
        <h3>{task.title}</h3>

        <p>{task.description}</p>
      </div>

      <div className="task-right">
        <span className="tag category">
          <span
            className="category-dot"
            style={{
              backgroundColor: task.category.color,
            }}
          />

          {task.category.name}
        </span>

        <span className={`tag priority ${priorityClass()}`}>
          <Flag size={14} />
          {priorityLabel()}
        </span>

        <span className="tag date">
          <Calendar size={14} />
          {dueDate}
        </span>

        <button className="icon-button" onClick={() => onEdit(task)}>
          <Pencil size={18} />
        </button>

        <button
          className="icon-button delete"
          onClick={() => onDelete(task.id)}
        >
          <Trash2 size={18} />
        </button>
      </div>
    </div>
  );
}

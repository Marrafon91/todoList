import './style.css';

import TaskCard from '../TaskCard';
import type { TaskDTO } from '../../models/task';

type Props = {
  tasks: TaskDTO[];
  onToggleDone: (task: TaskDTO) => void;
  onEdit: (task: TaskDTO) => void;
  onDelete: (id: number) => void;
};

export default function TaskList({
  tasks,
  onToggleDone,
  onEdit,
  onDelete,
}: Props) {
  if (tasks.length === 0) {
    return (
      <div className="task-list-empty">
        <h3>Nenhuma tarefa encontrada.</h3>
        <p>Adicione uma nova tarefa ou altere os filtros.</p>
      </div>
    );
  }

  return (
    <div className="task-list">
      {tasks.map((task) => (
        <TaskCard
          key={task.id}
          task={task}
          onToggleDone={onToggleDone}
          onEdit={onEdit}
          onDelete={onDelete}
        />
      ))}
    </div>
  );
}

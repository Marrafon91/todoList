import './style.css';
import type { TaskDTO } from '../../models/task';

type Props = {
  task: TaskDTO;
};

export default function TaskCard({ task }: Props) {
  return (
    <div className="task-card">
      <div>
        <h3>{task.title}</h3>
        <p>{task.description}</p>
      </div>
      <div className="task-tags">
        <span>{task.category.name}</span>
        <span>{task.priority}</span>
      </div>
    </div>
  );
}

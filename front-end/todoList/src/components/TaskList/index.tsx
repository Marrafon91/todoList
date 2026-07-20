import TaskCard from '../TaskCard';
import type { TaskDTO } from '../../models/task';

type Props = {
  tasks: TaskDTO[];
};

export default function TaskList({ tasks }: Props) {
  return (
    <div className="task-list">
      {tasks.map((task) => (
        <TaskCard key={task.id} task={task} />
      ))}
    </div>
  );
}

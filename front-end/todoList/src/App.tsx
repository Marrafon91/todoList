import { useEffect, useState } from 'react';
import { findAllTasks } from './services/task-service';
import type { TaskDTO } from './models/task';

export default function App() {
  const [tasks, setTasks] = useState<TaskDTO[]>([]);
  const [error, setError] = useState('');

  useEffect(() => {
    async function loadTasks() {
      try {
        const response = await findAllTasks();
        setTasks(response.data);
      } catch {
        setError("Não foi possível carregar as tarefas.");
      }
    }

    loadTasks();
  }, []);

  return (
    <div>
      <h1>Teste da API</h1>

      {tasks.map((task) => (
        <div key={task.id}>
          <h2>{task.title}</h2>
          <p>{task.description}</p>
          <p>{task.category.name}</p>
        </div>
      ))}
      {error && <p>{error}</p>}
    </div>
  );
}

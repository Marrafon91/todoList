import {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useState,
} from 'react';

import type { ReactNode } from 'react';

import type { DashboardDTO } from '../../models/dashboard';
import type { SidebarDTO } from '../../models/sidebar';
import type { TaskDTO, TaskInsertDTO, TaskUpdateDTO } from '../../models/task';

import { findDashboard } from '../../services/dashboard-service';
import { findSidebar } from '../../services/sidebar-service';

import {
  findAllTasks,
  insertTask,
  updateTask as updateTaskService,
  deleteTask as deleteTaskService,
  toggleDone,
} from '../../services/task-service';

type DashboardContextData = {
  dashboard?: DashboardDTO;
  sidebar?: SidebarDTO;
  tasks: TaskDTO[];
  search: string;
  setSearch: (value: string) => void;
  loading: boolean;
  refreshAll: () => Promise<void>;
  addTask: (dto: TaskInsertDTO) => Promise<void>;
  updateTask: (id: number, dto: TaskUpdateDTO) => Promise<void>;
  deleteTask: (id: number) => Promise<void>;
  toggleTaskDone: (task: TaskDTO) => Promise<void>;
};

const DashboardContext = createContext({} as DashboardContextData);

type Props = {
  children: ReactNode;
};

export function DashboardProvider({ children }: Props) {
  const [dashboard, setDashboard] = useState<DashboardDTO>();
  const [sidebar, setSidebar] = useState<SidebarDTO>();
  const [tasks, setTasks] = useState<TaskDTO[]>([]);
  const [search, setSearch] = useState('');
  const [loading, setLoading] = useState(true);

  const refreshAll = useCallback(async () => {
    try {
      setLoading(true);

      const [dashboardResponse, sidebarResponse, tasksResponse] =
        await Promise.all([
          findDashboard(),
          findSidebar(),
          findAllTasks(search),
        ]);

      setDashboard(dashboardResponse.data);
      setSidebar(sidebarResponse.data);

      setTasks(tasksResponse.data);
    } catch (error) {
      console.log(error);
    } finally {
      setLoading(false);
    }
  }, [search]);

  useEffect(() => {
    refreshAll();
  }, [refreshAll]);

  async function addTask(dto: TaskInsertDTO) {
    try {
      const response = await insertTask(dto);

      setTasks((previous) => [response.data, ...previous]);

      await Promise.all([refreshAll()]);
    } catch (error) {
      console.log(error);

      throw error;
    }
  }

  async function updateTask(id: number, dto: TaskUpdateDTO) {
    try {
      const response = await updateTaskService(id, dto);

      setTasks((previous) =>
        previous.map((task) => (task.id === id ? response.data : task)),
      );

      await Promise.all([refreshAll()]);
    } catch (error) {
      console.log(error);

      throw error;
    }
  }

  async function deleteTask(id: number) {
    try {
      await deleteTaskService(id);

      setTasks((previous) => previous.filter((task) => task.id !== id));

      await Promise.all([refreshAll()]);
    } catch (error) {
      console.log(error);

      throw error;
    }
  }

  async function toggleTaskDone(task: TaskDTO) {
    try {
      const response = await toggleDone(task.id);

      setTasks((previous) =>
        previous.map((item) => (item.id === task.id ? response.data : item)),
      );

      await Promise.all([refreshAll()]);
    } catch (error) {
      console.log(error);

      throw error;
    }
  }

  return (
    <DashboardContext.Provider
      value={{
        dashboard,
        sidebar,
        tasks,
        search,
        setSearch,
        loading,
        refreshAll,
        addTask,
        updateTask,
        deleteTask,
        toggleTaskDone,
      }}
    >
      {children}
    </DashboardContext.Provider>
  );
}

export function useDashboard() {
  return useContext(DashboardContext);
}

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
import type {
  TaskDTO,
  TaskFilterDTO,
  TaskInsertDTO,
  TaskUpdateDTO,
} from '../../models/task';

import { findDashboard } from '../../services/dashboard-service';
import { findSidebar } from '../../services/sidebar-service';

import {
  findAllTasks,
  insertTask,
  updateTask as updateTaskService,
  deleteTask as deleteTaskService,
  toggleDone,
} from '../../services/task-service';
import { useDebounce } from '../../hooks/useDebounce';

type DashboardContextData = {
  dashboard?: DashboardDTO;
  sidebar?: SidebarDTO;
  tasks: TaskDTO[];
  filters: TaskFilterDTO;
  setFilters: React.Dispatch<React.SetStateAction<TaskFilterDTO>>;
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
  const [loading, setLoading] = useState(true);

  const [filters, setFilters] = useState<TaskFilterDTO>({
    title: '',
    done: undefined,
    priority: undefined,
    categoryId: undefined,
  });

  const debouncedTitle = useDebounce(filters.title, 500);

  const refreshAll = useCallback(async () => {
    try {
      setLoading(true);

      const filtersRequest = {
        ...filters,
        title: debouncedTitle,
      };

      const [dashboardResponse, sidebarResponse, tasksResponse] =
        await Promise.all([
          findDashboard(),
          findSidebar(),
          findAllTasks(filtersRequest),
        ]);

      setDashboard(dashboardResponse.data);
      setSidebar(sidebarResponse.data);
      setTasks(tasksResponse.data);
    } finally {
      setLoading(false);
    }
  }, [debouncedTitle, filters.done, filters.priority, filters.categoryId]);

  useEffect(() => {
    refreshAll();
  }, [refreshAll]);

  async function addTask(dto: TaskInsertDTO) {
    try {
      const response = await insertTask(dto);
      setTasks((previous) => [response.data, ...previous]);

      const [dashboardResponse, sidebarResponse] = await Promise.all([
        findDashboard(),
        findSidebar(),
      ]);

      setDashboard(dashboardResponse.data);
      setSidebar(sidebarResponse.data);
    } catch (error) {
      console.log(error);
      throw error;
    }
  }

  async function updateTask(id: number, dto: TaskUpdateDTO) {
    try {
      await updateTaskService(id, dto);
      await refreshAll();
    } catch (error) {
      console.log(error);
      throw error;
    }
  }

  async function deleteTask(id: number) {
    try {
      await deleteTaskService(id);
      await refreshAll();
    } catch (error) {
      console.log(error);
      throw error;
    }
  }

  async function toggleTaskDone(task: TaskDTO) {
    try {
      await toggleDone(task.id);
      await refreshAll();
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
        filters,
        setFilters,
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

// eslint-disable-next-line react-refresh/only-export-components
export function useDashboard() {
  return useContext(DashboardContext);
}

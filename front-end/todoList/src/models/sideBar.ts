import type { CategorySummaryDTO } from './category';
import type { PrioritySummaryDTO } from './priority';

export type SidebarDTO = {
  totalTasks: number;
  pendingTasks: number;
  completedTasks: number;

  priorities: PrioritySummaryDTO[];
  categories: CategorySummaryDTO[];
};

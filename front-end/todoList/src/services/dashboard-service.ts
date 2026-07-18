import type { DashboardDTO } from "../models/dashboard";
import { api } from "../utils/api";


export function findDashboard() {
  return api.get<DashboardDTO>("/api/dashboard");
}

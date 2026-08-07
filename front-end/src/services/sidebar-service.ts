import type { SidebarDTO } from "../models/sidebar";
import { api } from "../utils/api";


export function findSidebar() {
  return api.get<SidebarDTO>("/api/sidebar");
}

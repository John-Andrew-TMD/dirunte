import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import { useDocumentTitle } from "@/hooks/useDocumentTitle";
import { constantRouterMap } from "./router.config";

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes:constantRouterMap,
});
router.beforeEach(async (to, from, next) => {
  useDocumentTitle(to.meta.title as string);
  next()
});
export default router;

import { Navigate } from "react-router-dom";
import { getToken, getRole } from "../utils/auth";

export default function PrivateRoute({ children }) {
  const token = getToken();
  const role = getRole();

  if (!token) return <Navigate to="/login" replace />;

  if (role !== "USER") return <Navigate to="/login" replace />;

  return children;
}
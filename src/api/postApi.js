const BASE_URL = "http://localhost:8080";

const authHeader = () => ({
  "Content-Type": "application/json",
  Authorization: `Bearer ${localStorage.getItem("token")}`,
});

// USER
export const createPost = async (content) => {
  await fetch(`${BASE_URL}/posts/create`, {
    method: "POST",
    headers: authHeader(),
    body: JSON.stringify({ content }),
  });
};

export const getMyPosts = async () => {
  const res = await fetch(`${BASE_URL}/posts/my`, { headers: authHeader() });
  return res.json();
};

export const getOtherPosts = async () => {
  const res = await fetch(`${BASE_URL}/posts/others`, { headers: authHeader() });
  return res.json();
};

// 🔥 ADMIN (FIXED URL)
export const getPendingPosts = async () => {
  const res = await fetch(`${BASE_URL}/admin/pending-posts`, {
    headers: authHeader(),
  });
  return res.json();
};

export const approvePost = async (id) => {
  await fetch(`${BASE_URL}/admin/approve/${id}`, {
    method: "PUT",
    headers: authHeader(),
  });
};

export const rejectPost = async (id) => {
  await fetch(`${BASE_URL}/admin/reject/${id}`, {
    method: "PUT",
    headers: authHeader(),
  });
};
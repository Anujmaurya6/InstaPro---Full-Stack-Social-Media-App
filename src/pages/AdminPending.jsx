import { useEffect, useState } from "react";
import { getPendingPosts, approvePost, rejectPost } from "../api/postApi";
import AdminNavbar from "../components/AdminNavbar";

export default function AdminPending() {
  const [posts, setPosts] = useState([]);

  const loadPosts = async () => {
    const data = await getPendingPosts();
    setPosts(data);
  };

  useEffect(() => {
    loadPosts();
  }, []);

  const handleApprove = async (id) => {
    await approvePost(id);
    loadPosts();
  };

  const handleReject = async (id) => {
    await rejectPost(id);
    loadPosts();
  };

  return (
    <div className="min-h-screen bg-orange-50">

      {/* ✅ AdminNavbar - pendingCount live update hoga */}
      <AdminNavbar pendingCount={posts.length} />

      <div className="p-6 max-w-2xl mx-auto">
        <h2 className="text-2xl font-bold mb-4">Pending Posts</h2>

        {posts.length === 0 && (
          <p className="text-gray-500">No pending posts 🎉</p>
        )}

        {posts.map((post) => (
          <div
            key={post.id}
            className="bg-white p-4 mb-4 rounded-xl shadow"
          >
            <p className="mb-2">{post.content}</p>
            <small className="text-gray-500">
              {new Date(post.createdAt).toLocaleString()}
            </small>

            <div className="mt-3 flex gap-4">
              <button
                onClick={() => handleApprove(post.id)}
                className="bg-green-500 text-white px-4 py-1 rounded"
              >
                Approve
              </button>

              <button
                onClick={() => handleReject(post.id)}
                className="bg-red-500 text-white px-4 py-1 rounded"
              >
                Reject
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

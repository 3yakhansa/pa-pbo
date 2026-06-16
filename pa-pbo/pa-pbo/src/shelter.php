<!DOCTYPE html>
<html lang="id">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>PawPatrol Shelter 🐾</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,600;0,700;1,400&family=Nunito:wght@400;500;600;700;800&display=swap" rel="stylesheet">
<style>
  :root {
    --cream: #FAF6EF;
    --cream-dark: #F2EBD9;
    --sand: #E8D9BC;
    --warm-brown: #8B6343;
    --deep-brown: #5C3D1E;
    --terracotta: #C4714A;
    --terracotta-light: #F0C4A8;
    --sage: #7A9E7E;
    --sage-light: #C8DFC9;
    --dusty-rose: #D4818A;
    --dusty-rose-light: #F0C8CC;
    --sky: #7BAFC4;
    --sky-light: #C8E0EC;
    --text-main: #3D2B1A;
    --text-muted: #7A6553;
    --card-bg: #FFFDF9;
    --border: #DDD0B8;
    --tag-vaksin: #C8DFC9;
    --tag-vaksin-text: #2D6133;
    --tag-novaksin: #F8DDD0;
    --tag-novaksin-text: #8B3A1E;
    --tag-tersedia: #C8E0EC;
    --tag-tersedia-text: #1A5570;
    --tag-diadopsi: #F0C8CC;
    --tag-diadopsi-text: #7A2430;
    --shadow-soft: 0 4px 24px rgba(92,61,30,0.09);
    --shadow-hover: 0 8px 36px rgba(92,61,30,0.15);
  }

  * { box-sizing: border-box; margin: 0; padding: 0; }

  body {
    font-family: 'Nunito', sans-serif;
    background-color: var(--cream);
    color: var(--text-main);
    min-height: 100vh;
    overflow-x: hidden;
  }

  /* ─── BG TEXTURE ─── */
  body::before {
    content: '';
    position: fixed; inset: 0; z-index: 0;
    background-image:
      radial-gradient(circle at 15% 20%, rgba(196,113,74,0.07) 0%, transparent 45%),
      radial-gradient(circle at 85% 80%, rgba(122,158,126,0.08) 0%, transparent 45%),
      radial-gradient(circle at 50% 50%, rgba(212,129,138,0.05) 0%, transparent 60%);
    pointer-events: none;
  }

  /* ─── PAWS FLOAT ─── */
  .paws-bg {
    position: fixed; inset: 0; z-index: 0; pointer-events: none; overflow: hidden;
  }
  .paw {
    position: absolute;
    font-size: 1.8rem;
    opacity: 0.04;
    animation: floatPaw 18s ease-in-out infinite;
  }
  .paw:nth-child(1)  { top: 8%;  left: 5%;  animation-delay: 0s;   font-size: 2.2rem; }
  .paw:nth-child(2)  { top: 20%; left: 88%; animation-delay: 3s;   font-size: 1.4rem; }
  .paw:nth-child(3)  { top: 45%; left: 92%; animation-delay: 6s;   font-size: 2.8rem; }
  .paw:nth-child(4)  { top: 72%; left: 3%;  animation-delay: 9s;   font-size: 1.6rem; }
  .paw:nth-child(5)  { top: 88%; left: 75%; animation-delay: 12s;  font-size: 2rem;   }
  .paw:nth-child(6)  { top: 35%; left: 50%; animation-delay: 4s;   font-size: 1.2rem; }
  @keyframes floatPaw {
    0%,100% { transform: translateY(0) rotate(-10deg); }
    50%      { transform: translateY(-18px) rotate(8deg); }
  }

  /* ─── LAYOUT ─── */
  .app-wrap { position: relative; z-index: 1; }

  /* ─── HEADER ─── */
  header {
    background: linear-gradient(135deg, var(--deep-brown) 0%, var(--warm-brown) 100%);
    padding: 0;
    position: sticky; top: 0; z-index: 100;
    box-shadow: 0 2px 16px rgba(92,61,30,0.25);
  }
  .header-inner {
    max-width: 1100px; margin: 0 auto;
    display: flex; align-items: center; justify-content: space-between;
    padding: 0.85rem 2rem;
  }
  .logo {
    display: flex; align-items: center; gap: 0.7rem;
    font-family: 'Lora', serif;
    font-size: 1.5rem; font-weight: 700;
    color: var(--cream); text-decoration: none;
  }
  .logo-icon { font-size: 1.9rem; }
  .logo span { color: var(--terracotta-light); }
  .user-badge {
    display: flex; align-items: center; gap: 0.6rem;
    background: rgba(255,255,255,0.12);
    border: 1px solid rgba(255,255,255,0.2);
    border-radius: 999px; padding: 0.4rem 1rem;
    color: var(--cream); font-size: 0.9rem; font-weight: 600;
    cursor: pointer; transition: background 0.2s;
  }
  .user-badge:hover { background: rgba(255,255,255,0.2); }

  /* ─── HERO ─── */
  .hero {
    background: linear-gradient(160deg, var(--deep-brown) 0%, var(--warm-brown) 55%, var(--terracotta) 100%);
    padding: 5rem 2rem 6rem;
    text-align: center;
    position: relative; overflow: hidden;
  }
  .hero::after {
    content: '';
    position: absolute; bottom: -2px; left: 0; right: 0;
    height: 60px;
    background: var(--cream);
    clip-path: ellipse(55% 100% at 50% 100%);
  }
  .hero-emoji { font-size: 5rem; display: block; margin-bottom: 1.2rem; animation: catBounce 2.5s ease-in-out infinite; }
  @keyframes catBounce {
    0%,100% { transform: translateY(0) rotate(-3deg); }
    50%      { transform: translateY(-12px) rotate(3deg); }
  }
  .hero h1 {
    font-family: 'Lora', serif;
    font-size: clamp(2rem, 5vw, 3.2rem);
    font-weight: 700; color: #fff; margin-bottom: 0.8rem;
    text-shadow: 0 2px 12px rgba(0,0,0,0.15);
  }
  .hero h1 em { color: var(--terracotta-light); font-style: italic; }
  .hero p {
    font-size: 1.1rem; color: rgba(255,255,255,0.82);
    max-width: 520px; margin: 0 auto 2rem; line-height: 1.7;
  }
  .hero-stats {
    display: flex; justify-content: center; gap: 2.5rem;
    flex-wrap: wrap; margin-top: 1.5rem;
  }
  .stat-pill {
    background: rgba(255,255,255,0.14);
    border: 1px solid rgba(255,255,255,0.22);
    border-radius: 999px; padding: 0.55rem 1.4rem;
    color: #fff; font-size: 0.9rem; font-weight: 600;
  }
  .stat-pill strong { font-size: 1.15rem; display: block; text-align: center; }

  /* ─── NAV TABS ─── */
  .main-nav {
    background: var(--card-bg);
    border-bottom: 2px solid var(--sand);
    position: sticky; top: 60px; z-index: 90;
  }
  .main-nav-inner {
    max-width: 1100px; margin: 0 auto;
    display: flex; gap: 0;
    padding: 0 2rem;
    overflow-x: auto;
  }
  .nav-tab {
    padding: 1rem 1.5rem;
    font-weight: 700; font-size: 0.95rem;
    color: var(--text-muted); cursor: pointer;
    border-bottom: 3px solid transparent;
    transition: all 0.2s; white-space: nowrap;
    background: none; border-left: none; border-right: none; border-top: none;
    display: flex; align-items: center; gap: 0.4rem;
  }
  .nav-tab:hover { color: var(--terracotta); }
  .nav-tab.active { color: var(--terracotta); border-bottom-color: var(--terracotta); }

  /* ─── SECTIONS ─── */
  .section { display: none; }
  .section.active { display: block; }
  .section-inner { max-width: 1100px; margin: 0 auto; padding: 2.5rem 2rem; }
  .section-title {
    font-family: 'Lora', serif; font-size: 1.7rem; font-weight: 700;
    color: var(--deep-brown); margin-bottom: 0.4rem;
  }
  .section-sub {
    font-size: 0.95rem; color: var(--text-muted); margin-bottom: 2rem;
  }

  /* ─── FILTER BAR ─── */
  .filter-bar {
    display: flex; gap: 0.6rem; flex-wrap: wrap; margin-bottom: 1.8rem; align-items: center;
  }
  .filter-btn {
    padding: 0.45rem 1.1rem; border-radius: 999px;
    border: 1.5px solid var(--border); background: var(--card-bg);
    font-family: 'Nunito', sans-serif; font-size: 0.88rem; font-weight: 700;
    color: var(--text-muted); cursor: pointer; transition: all 0.2s;
  }
  .filter-btn:hover { border-color: var(--terracotta); color: var(--terracotta); }
  .filter-btn.active { background: var(--terracotta); border-color: var(--terracotta); color: #fff; }
  .search-wrap { margin-left: auto; }
  .search-wrap input {
    padding: 0.45rem 1rem; border-radius: 999px;
    border: 1.5px solid var(--border); background: var(--card-bg);
    font-family: 'Nunito', sans-serif; font-size: 0.88rem;
    color: var(--text-main); outline: none; width: 200px;
    transition: border 0.2s;
  }
  .search-wrap input:focus { border-color: var(--terracotta); }
  .search-wrap input::placeholder { color: var(--text-muted); }

  /* ─── CAT CARDS GRID ─── */
  .cat-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
    gap: 1.5rem;
  }
  .cat-card {
    background: var(--card-bg);
    border-radius: 20px;
    border: 1.5px solid var(--border);
    box-shadow: var(--shadow-soft);
    overflow: hidden;
    transition: transform 0.25s, box-shadow 0.25s;
    cursor: default;
  }
  .cat-card:hover { transform: translateY(-5px); box-shadow: var(--shadow-hover); }
  .cat-card-art {
    height: 150px;
    display: flex; align-items: center; justify-content: center;
    font-size: 4.5rem;
    position: relative; overflow: hidden;
  }
  .cat-card-art.domestik { background: linear-gradient(135deg, #FDE8D5, #F5CCAA); }
  .cat-card-art.langka   { background: linear-gradient(135deg, #DDE8F5, #B8CFE8); }
  .cat-card-art .kandang-badge {
    position: absolute; top: 10px; left: 12px;
    background: rgba(255,255,255,0.75);
    border-radius: 999px; padding: 2px 10px;
    font-size: 0.72rem; font-weight: 700;
    color: var(--text-muted);
    backdrop-filter: blur(4px);
  }
  .cat-card-art .jenis-badge {
    position: absolute; top: 10px; right: 12px;
    border-radius: 999px; padding: 2px 10px;
    font-size: 0.72rem; font-weight: 700;
  }
  .jenis-badge.domestik { background: var(--terracotta-light); color: var(--deep-brown); }
  .jenis-badge.langka   { background: var(--sky-light); color: #1A4560; }

  .cat-card-body { padding: 1.1rem 1.3rem 1.3rem; }
  .cat-card-name {
    font-family: 'Lora', serif; font-size: 1.2rem; font-weight: 700;
    color: var(--deep-brown); margin-bottom: 0.2rem;
  }
  .cat-card-ras { font-size: 0.85rem; color: var(--text-muted); margin-bottom: 0.8rem; }
  .cat-card-meta {
    display: flex; gap: 0.5rem; flex-wrap: wrap; margin-bottom: 0.9rem;
  }
  .meta-chip {
    font-size: 0.78rem; font-weight: 700;
    border-radius: 999px; padding: 3px 10px;
  }
  .chip-usia   { background: var(--sand); color: var(--deep-brown); }
  .chip-warna  { background: var(--cream-dark); color: var(--text-muted); }
  .chip-status-tersedia { background: var(--tag-tersedia); color: var(--tag-tersedia-text); }
  .chip-status-diadopsi { background: var(--tag-diadopsi); color: var(--tag-diadopsi-text); }
  .chip-vaksin   { background: var(--tag-vaksin); color: var(--tag-vaksin-text); }
  .chip-novaksin { background: var(--tag-novaksin); color: var(--tag-novaksin-text); }
  .chip-biaya  { background: var(--sage-light); color: #2D5E34; }

  .cat-card-actions { display: flex; gap: 0.5rem; margin-top: 0.5rem; }
  .btn {
    font-family: 'Nunito', sans-serif; font-weight: 700;
    border-radius: 10px; padding: 0.5rem 1rem; font-size: 0.85rem;
    cursor: pointer; border: none; transition: all 0.2s;
    display: inline-flex; align-items: center; gap: 0.3rem;
  }
  .btn-primary { background: var(--terracotta); color: #fff; flex: 1; justify-content: center; }
  .btn-primary:hover { background: var(--deep-brown); }
  .btn-outline {
    background: transparent; color: var(--text-muted);
    border: 1.5px solid var(--border); padding: 0.5rem 0.7rem;
  }
  .btn-outline:hover { border-color: var(--terracotta); color: var(--terracotta); }
  .btn-sm { padding: 0.35rem 0.8rem; font-size: 0.8rem; border-radius: 8px; }
  .btn-danger { background: #C9393A; color: #fff; }
  .btn-danger:hover { background: #9E2A2B; }
  .btn-success { background: var(--sage); color: #fff; }
  .btn-success:hover { background: #4D7A52; }

  .empty-state {
    text-align: center; padding: 4rem 2rem;
    color: var(--text-muted); font-size: 1rem;
  }
  .empty-state .emoji { font-size: 3.5rem; display: block; margin-bottom: 1rem; }

  /* ─── FORM CARD ─── */
  .form-card {
    background: var(--card-bg); border: 1.5px solid var(--border);
    border-radius: 20px; box-shadow: var(--shadow-soft);
    padding: 2rem 2rem 1.5rem; max-width: 700px; margin-bottom: 2rem;
  }
  .form-card h3 {
    font-family: 'Lora', serif; font-size: 1.2rem; font-weight: 700;
    color: var(--deep-brown); margin-bottom: 1.3rem;
    display: flex; align-items: center; gap: 0.4rem;
  }
  .form-row { display: grid; gap: 1rem; margin-bottom: 1rem; }
  .form-row.two { grid-template-columns: 1fr 1fr; }
  .form-row.three { grid-template-columns: 1fr 1fr 1fr; }
  @media(max-width:600px) { .form-row.two, .form-row.three { grid-template-columns: 1fr; } }
  .form-group { display: flex; flex-direction: column; gap: 0.35rem; }
  label { font-size: 0.85rem; font-weight: 700; color: var(--text-muted); }
  input, select, textarea {
    font-family: 'Nunito', sans-serif; font-size: 0.95rem;
    padding: 0.6rem 0.9rem; border-radius: 10px;
    border: 1.5px solid var(--border); background: var(--cream);
    color: var(--text-main); outline: none; transition: border 0.2s;
    width: 100%;
  }
  input:focus, select:focus, textarea:focus { border-color: var(--terracotta); background: #fff; }
  input.error, select.error { border-color: #C9393A; }
  .field-error { font-size: 0.78rem; color: #C9393A; font-weight: 600; display: none; }
  .field-error.show { display: block; }
  .hint { font-size: 0.78rem; color: var(--text-muted); }

  /* ─── ADOPSI TABLE ─── */
  .adopsi-list { display: flex; flex-direction: column; gap: 1.2rem; }
  .adopsi-card {
    background: var(--card-bg); border: 1.5px solid var(--border);
    border-radius: 16px; box-shadow: var(--shadow-soft);
    padding: 1.3rem 1.5rem;
    display: grid; grid-template-columns: auto 1fr auto;
    gap: 1rem; align-items: center;
  }
  .adopsi-id {
    background: var(--terracotta); color: #fff;
    border-radius: 12px; padding: 0.5rem 0.8rem;
    font-size: 0.8rem; font-weight: 800; text-align: center;
    min-width: 54px;
  }
  .adopsi-id span { font-size: 1.1rem; display: block; }
  .adopsi-info h4 { font-family: 'Lora', serif; font-size: 1.05rem; font-weight: 700; color: var(--deep-brown); }
  .adopsi-info p  { font-size: 0.85rem; color: var(--text-muted); margin-top: 0.2rem; line-height: 1.5; }
  .adopsi-cat {
    background: var(--cream-dark); border-radius: 10px; padding: 0.5rem 0.8rem;
    text-align: center; min-width: 100px;
  }
  .adopsi-cat .cat-face { font-size: 1.8rem; }
  .adopsi-cat p { font-size: 0.78rem; font-weight: 700; color: var(--text-muted); }

  /* ─── AUTH PAGE ─── */
  .auth-overlay {
    position: fixed; inset: 0; z-index: 200;
    background: linear-gradient(150deg, var(--deep-brown), var(--warm-brown) 50%, var(--terracotta));
    display: flex; align-items: center; justify-content: center; padding: 1rem;
  }
  .auth-box {
    background: var(--card-bg); border-radius: 24px;
    padding: 2.5rem; max-width: 420px; width: 100%;
    box-shadow: 0 20px 60px rgba(0,0,0,0.35);
    animation: slideUp 0.4s ease;
  }
  @keyframes slideUp { from { transform: translateY(30px); opacity: 0; } to { transform: translateY(0); opacity: 1; } }
  .auth-logo { text-align: center; margin-bottom: 1.5rem; }
  .auth-logo .big-cat { font-size: 4rem; display: block; animation: catBounce 2.5s ease-in-out infinite; }
  .auth-logo h2 {
    font-family: 'Lora', serif; font-size: 1.6rem; font-weight: 700;
    color: var(--deep-brown); margin-top: 0.5rem;
  }
  .auth-logo p { font-size: 0.9rem; color: var(--text-muted); }
  .auth-tabs { display: flex; gap: 0; margin-bottom: 1.5rem; border-radius: 12px; overflow: hidden; border: 1.5px solid var(--border); }
  .auth-tab {
    flex: 1; padding: 0.65rem; text-align: center;
    font-weight: 700; font-size: 0.9rem;
    cursor: pointer; transition: all 0.2s;
    background: transparent; color: var(--text-muted); border: none;
    font-family: 'Nunito', sans-serif;
  }
  .auth-tab.active { background: transparent; color: var(--deep-brown); }
  .auth-error { background: #FDE8E8; border: 1px solid #F0AAAA; border-radius: 10px; padding: 0.6rem 1rem; font-size: 0.85rem; color: #8B1A1A; margin-bottom: 1rem; display: none; }
  .auth-error.show { display: block; }
  .auth-success { background: var(--sage-light); border: 1px solid #A8CCA8; border-radius: 10px; padding: 0.6rem 1rem; font-size: 0.85rem; color: #1E5E26; margin-bottom: 1rem; display: none; }
  .auth-success.show { display: block; }

  /* ─── TOAST ─── */
  #toast {
    position: fixed; bottom: 2rem; left: 50%; transform: translateX(-50%) translateY(80px);
    background: var(--deep-brown); color: #fff;
    border-radius: 12px; padding: 0.75rem 1.5rem;
    font-size: 0.9rem; font-weight: 600; z-index: 999;
    transition: transform 0.35s cubic-bezier(0.34, 1.56, 0.64, 1);
    pointer-events: none; white-space: nowrap;
  }
  #toast.show { transform: translateX(-50%) translateY(0); }

  /* ─── MODAL ─── */
  .modal-overlay {
    position: fixed; inset: 0; z-index: 150;
    background: rgba(0,0,0,0.45);
    display: flex; align-items: center; justify-content: center; padding: 1rem;
    display: none;
  }
  .modal-overlay.open { display: flex; }
  .modal-box {
    background: var(--card-bg); border-radius: 20px;
    padding: 2rem; max-width: 520px; width: 100%;
    box-shadow: 0 20px 60px rgba(0,0,0,0.25);
    animation: slideUp 0.3s ease;
    max-height: 90vh; overflow-y: auto;
  }
  .modal-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 1.5rem; }
  .modal-header h3 { font-family: 'Lora', serif; font-size: 1.25rem; color: var(--deep-brown); }
  .modal-close { background: none; border: none; font-size: 1.4rem; cursor: pointer; color: var(--text-muted); }

  /* ─── BIAYA CALC ─── */
  .biaya-wrap {
    background: var(--sage-light); border-radius: 12px; padding: 1rem;
    margin-top: 1rem; text-align: center;
  }
  .biaya-wrap .biaya-label { font-size: 0.82rem; color: #2D5E34; font-weight: 600; }
  .biaya-wrap .biaya-num { font-size: 1.5rem; font-weight: 800; color: #1A4020; margin-top: 0.2rem; }

  /* ─── RESPONSIVE ─── */
  @media(max-width: 700px) {
    .hero { padding: 3.5rem 1.5rem 5rem; }
    .section-inner { padding: 1.5rem 1rem; }
    .adopsi-card { grid-template-columns: 1fr; }
  }
</style>
</head>
<body>

<!-- Paws background -->
<div class="paws-bg">
  <div class="paw">🐾</div><div class="paw">🐾</div><div class="paw">🐾</div>
  <div class="paw">🐾</div><div class="paw">🐾</div><div class="paw">🐾</div>
</div>

<!-- ─── AUTH OVERLAY ─── -->
<div class="auth-overlay" id="authOverlay">
  <div class="auth-box">
    <div class="auth-logo">
      <span class="big-cat">🐱</span>
      <h2>PawPatrol Shelter</h2>
      <p>Temukan kucing pilihanmu hari ini 🐾</p>
    </div>
    <div class="auth-tabs">
      <button class="auth-tab active" id="tabLogin" onclick="switchAuthTab('login')">Login</button>
    </div>
    <div class="auth-error" id="authError"></div>
    <div class="auth-success" id="authSuccess"></div>

    <!-- Login Form -->
    <div id="loginForm">
      <div class="form-group" style="margin-bottom:1rem">
        <label>Username</label>
        <input type="text" id="loginUser" placeholder="Masukkan username...">
      </div>
      <div class="form-group" style="margin-bottom:1.5rem">
        <label>Password</label>
        <input type="password" id="loginPass" placeholder="Masukkan password...">
      </div>
      <button class="btn btn-primary" style="width:100%; padding:0.75rem; font-size:1rem; border-radius:12px;" onclick="doLogin()">🔑 Masuk</button>
    </div>

    <!-- Register removed -->
  </div>
</div>

<!-- ─── MAIN APP ─── -->
<div class="app-wrap" id="mainApp" style="display:none">

  <!-- HEADER -->
  <header>
    <div class="header-inner">
      <a href="#" class="logo"><span class="logo-icon">🐾</span>Paw<span>Patrol</span></a>
      <div class="user-badge" onclick="doLogout()">
        <span>👤</span> <span id="headerUser">User</span> &nbsp;· Keluar
      </div>
    </div>
  </header>

  <!-- HERO -->
  <div class="hero">
    <span class="hero-emoji">🐱</span>
    <h1>Selamat Datang di<br><em>PawPatrol Shelter</em></h1>
    <p>Temukan kucing berbulu yang menunggu rumah hangat dan keluarga penyayang. Mari adopsi, beri mereka cinta!</p>
    <div class="hero-stats">
      <div class="stat-pill">🐈 <strong id="statTotal">0</strong> Kucing</div>
      <div class="stat-pill">✅ <strong id="statTersedia">0</strong> Tersedia</div>
      <div class="stat-pill">🤝 <strong id="statAdopsi">0</strong> Teradopsi</div>
    </div>
  </div>

  <!-- NAV TABS -->
  <nav class="main-nav">
    <div class="main-nav-inner">
      <button class="nav-tab active" onclick="showSection('kucing')">🐈 Data Kucing</button>
      <button class="nav-tab" onclick="showSection('tambah')">➕ Tambah Kucing</button>
      <button class="nav-tab" onclick="showSection('adopsi')">🤝 Adopsi</button>
      <button class="nav-tab" onclick="showSection('riwayat')">📋 Riwayat Adopsi</button>
    </div>
  </nav>

  <!-- ════════ SECTION: DATA KUCING ════════ -->
  <section class="section active" id="sec-kucing">
    <div class="section-inner">
      <h2 class="section-title">🐈 Daftar Kucing</h2>
      <p class="section-sub">Semua penghuni shelter yang menunggu untuk diadopsi</p>
      <div class="filter-bar">
        <button class="filter-btn active" onclick="filterKucing('semua',this)">Semua</button>
        <button class="filter-btn" onclick="filterKucing('domestik',this)">Domestik</button>
        <button class="filter-btn" onclick="filterKucing('langka',this)">Langka</button>
        <button class="filter-btn" onclick="filterKucing('tersedia',this)">✅ Tersedia</button>
        <button class="filter-btn" onclick="filterKucing('diadopsi',this)">💙 Diadopsi</button>
        <div class="search-wrap">
          <input type="text" id="searchInput" placeholder="🔍 Cari nama..." oninput="filterKucing('search',null)">
        </div>
      </div>
      <div class="cat-grid" id="catGrid"></div>
    </div>
  </section>

  <!-- ════════ SECTION: TAMBAH KUCING ════════ -->
  <section class="section" id="sec-tambah">
    <div class="section-inner">
      <h2 class="section-title">➕ Tambah Kucing Baru</h2>
      <p class="section-sub">Daftarkan kucing baru ke dalam sistem shelter</p>
      <div class="form-card">
        <h3>🐱 Data Kucing</h3>
        <div class="form-row two">
          <div class="form-group">
            <label>Nama Kucing</label>
            <input type="text" id="fNama" placeholder="cth: Luna, Mochi...">
            <span class="field-error" id="e-fNama">Nama tidak boleh kosong</span>
          </div>
          <div class="form-group">
            <label>Warna</label>
            <input type="text" id="fWarna" placeholder="cth: Putih, Orange...">
            <span class="field-error" id="e-fWarna">Warna tidak boleh kosong</span>
          </div>
        </div>
        <div class="form-row two">
          <div class="form-group">
            <label>Usia (bulan)</label>
            <input type="number" id="fUsia" placeholder="cth: 12" min="1">
            <span class="field-error" id="e-fUsia">Usia harus lebih dari 0</span>
          </div>
          <div class="form-group">
            <label>Kategori</label>
            <select id="fKategori" onchange="updateRasField()">
              <option value="">-- Pilih Kategori --</option>
              <option value="domestik">Domestik</option>
              <option value="langka">Kucing Ras</option>
            </select>
            <span class="field-error" id="e-fKategori">Pilih kategori</span>
          </div>
        </div>
        <div class="form-row two" id="rasRow" style="display:none">
          <div class="form-group">
            <label>Ras</label>
            <select id="fRas">
              <option value="Persia">Persia</option>
              <option value="Anggora">Anggora</option>
              <option value="Sphynx">Sphynx</option>
              <option value="Maine Coon">Maine Coon</option>
              <option value="Siamese">Siamese</option>
              <option value="Ragdoll">Ragdoll</option>
              <option value="Bengal">Bengal</option>
              <option value="Munchkin">Munchkin</option>
              <option value="Birman">Birman</option>
              <option value="Scottish Fold">Scottish Fold</option>
            </select>
          </div>
        </div>
        <div class="form-row" style="margin-bottom:1.2rem">
          <div class="form-group">
            <label>Status Vaksinasi</label>
            <select id="fVaksin">
              <option value="true">✅ Sudah Lengkap</option>
              <option value="false">⚠️ Belum / Perlu Update</option>
            </select>
          </div>
        </div>
        <!-- Biaya estimasi -->
        <div class="biaya-wrap" id="biayaPreview" style="display:none">
          <div class="biaya-label">💰 Estimasi Biaya Perawatan / Bulan</div>
          <div class="biaya-num" id="biayaNum">Rp 0</div>
        </div>
        <div style="margin-top:1.3rem; display:flex; gap:0.7rem;">
          <button class="btn btn-primary" onclick="tambahKucing()">✅ Simpan Kucing</button>
          <button class="btn btn-outline" onclick="resetFormTambah()">🔄 Reset</button>
        </div>
      </div>
    </div>
  </section>

  <!-- ════════ SECTION: ADOPSI ════════ -->
  <section class="section" id="sec-adopsi">
    <div class="section-inner">
      <h2 class="section-title">🤝 Buat Adopsi</h2>
      <p class="section-sub">Catat proses adopsi kucing dari shelter</p>
      <div class="form-card">
        <h3>📋 Form Adopsi</h3>
        <div class="form-row">
          <div class="form-group">
            <label>Pilih Kucing (Status: Tersedia)</label>
            <select id="aKucing">
              <option value="">-- Pilih Kucing --</option>
            </select>
            <span class="field-error" id="e-aKucing">Pilih kucing terlebih dahulu</span>
          </div>
        </div>
        <div class="form-row two">
          <div class="form-group">
            <label>Nama Pengadopsi</label>
            <input type="text" id="aNama" placeholder="Nama lengkap...">
            <span class="field-error" id="e-aNama">Nama tidak boleh kosong</span>
          </div>
          <div class="form-group">
            <label>No. HP</label>
            <input type="text" id="aHp" placeholder="08xxxxxxxxxx">
            <span class="hint">Format: 08xxxxxxxxxx (10-14 digit)</span>
            <span class="field-error" id="e-aHp">Format nomor tidak valid</span>
          </div>
        </div>
        <div class="form-row two">
          <div class="form-group">
            <label>Tanggal Adopsi</label>
            <input type="date" id="aTglAdopsi">
            <span class="field-error" id="e-aTglAdopsi">Masukkan tanggal adopsi</span>
          </div>
          <div class="form-group">
            <label>Tanggal Keluar Shelter</label>
            <input type="date" id="aTglKeluar">
            <span class="field-error" id="e-aTglKeluar">Tanggal keluar tidak valid</span>
          </div>
        </div>
        <button class="btn btn-primary" style="margin-top:0.5rem" onclick="buatAdopsi()">🐾 Proses Adopsi</button>
      </div>
    </div>
  </section>

  <!-- ════════ SECTION: RIWAYAT ════════ -->
  <section class="section" id="sec-riwayat">
    <div class="section-inner">
      <h2 class="section-title">📋 Riwayat Adopsi</h2>
      <p class="section-sub">Semua kucing yang sudah menemukan rumah baru</p>
      <div class="adopsi-list" id="adopsiList"></div>
    </div>
  </section>

</div><!-- end #mainApp -->

<!-- ─── EDIT MODAL ─── -->
<div class="modal-overlay" id="editModal">
  <div class="modal-box">
    <div class="modal-header">
      <h3>✏️ Edit Data Kucing</h3>
      <button class="modal-close" onclick="closeModal()">✕</button>
    </div>
    <input type="hidden" id="editId">
    <div class="form-row two">
      <div class="form-group">
        <label>Nama</label>
        <input type="text" id="editNama">
      </div>
      <div class="form-group">
        <label>Usia (bulan)</label>
        <input type="number" id="editUsia" min="1">
      </div>
    </div>
    <div class="form-group" style="margin-bottom:1.3rem">
      <label>Warna</label>
      <input type="text" id="editWarna">
    </div>
    <div style="display:flex; gap:0.7rem">
      <button class="btn btn-primary" onclick="simpanEdit()">💾 Simpan</button>
      <button class="btn btn-outline" onclick="closeModal()">Batal</button>
    </div>
  </div>
</div>

<!-- ─── TOAST ─── -->
<div id="toast"></div>

<script>
// ══════════════════════════════════════════════
//  STATE
// ══════════════════════════════════════════════
let users = [];
let currentUser = null;
let idCounter = 1;
let adopsiCounter = 1;
let kandangCounter = 100;
let cats = [];
let adopsiList = [];

// Cat emojis by fur color pattern
const catEmojis = {
  'orange': '🟠', 'putih': '🤍', 'hitam': '🖤', 'abu': '🩶', 'coklat': '🤎', 'kuning': '💛',
};
const catFaces = ['😸', '😺', '🐱', '😻', '🙀', '😼', '🐈'];
function catFace(id) { return catFaces[id % catFaces.length]; }

// Init data (mirrors Java Main.java initial data)
function initData() {
  users.push({ username: 'admin', password: 'admin123', role: 'Admin' });
  users.push({ username: 'penjaga1', password: 'penjaga111', role: 'Penjaga' });
  users.push({ username: 'user1', password: 'user123', role: 'Penjaga' });

  cats.push({ id: idCounter++, nama: 'Oren', ras: 'Domestik', usia: 12, status: 'Tersedia', warna: 'Orange', jenis: 'domestik', vaksin: true, kandang: kandangCounter++ });
  cats.push({ id: idCounter++, nama: 'Mochi', ras: 'Persia', usia: 24, status: 'Tersedia', warna: 'Putih', jenis: 'langka', vaksin: true, kandang: kandangCounter++ });
}

// ══════════════════════════════════════════════
//  AUTH
// ══════════════════════════════════════════════
function switchAuthTab(tab) {
  document.getElementById('loginForm').style.display    = tab === 'login'    ? 'block' : 'none';
  document.getElementById('tabLogin').classList.toggle('active', tab === 'login');
  clearAuthMsg();
}
function showAuthError(msg) {
  const el = document.getElementById('authError');
  el.textContent = msg; el.classList.add('show');
  document.getElementById('authSuccess').classList.remove('show');
}
function showAuthSuccess(msg) {
  const el = document.getElementById('authSuccess');
  el.textContent = msg; el.classList.add('show');
  document.getElementById('authError').classList.remove('show');
}
function clearAuthMsg() {
  document.getElementById('authError').classList.remove('show');
  document.getElementById('authSuccess').classList.remove('show');
}
function doLogin() {
  const u = document.getElementById('loginUser').value.trim();
  const p = document.getElementById('loginPass').value;
  if (!u || !p) { showAuthError('❌ Username dan password tidak boleh kosong.'); return; }
  const user = users.find(x => x.username === u && x.password === p);
  if (!user) { showAuthError('❌ Username atau password salah.'); return; }
  currentUser = user;
  document.getElementById('authOverlay').style.display = 'none';
  document.getElementById('mainApp').style.display = 'block';
  document.getElementById('headerUser').textContent = u;
  renderCats(); updateStats();
  showToast('✅ Selamat datang, ' + u + '!');
}
// Registration removed
function doLogout() {
  currentUser = null;
  document.getElementById('authOverlay').style.display = 'flex';
  document.getElementById('mainApp').style.display = 'none';
  document.getElementById('loginUser').value = '';
  document.getElementById('loginPass').value = '';
  clearAuthMsg();
}

// ══════════════════════════════════════════════
//  NAVIGATION
// ══════════════════════════════════════════════
function showSection(name) {
  document.querySelectorAll('.section').forEach(s => s.classList.remove('active'));
  document.querySelectorAll('.nav-tab').forEach(t => t.classList.remove('active'));
  document.getElementById('sec-' + name).classList.add('active');
  event.currentTarget.classList.add('active');
  if (name === 'adopsi') populateAdopsiSelect();
  if (name === 'riwayat') renderAdopsi();
  if (name === 'kucing') renderCats();
}

// ══════════════════════════════════════════════
//  BIAYA HELPER
// ══════════════════════════════════════════════
function hitungBiaya(jenis, vaksin, bulan, extraCare) {
  let cost;
  if (jenis === 'domestik') { cost = bulan * 75000; if (vaksin) cost *= 0.9; }
  else                      { cost = bulan * 75000 * 1.5; }
  if (extraCare) cost += 50000;
  return cost;
}

// ══════════════════════════════════════════════
//  RENDER CATS
// ══════════════════════════════════════════════
let activeFilter = 'semua';
function filterKucing(type, btn) {
  if (btn) {
    document.querySelectorAll('.filter-btn').forEach(b => b.classList.remove('active'));
    btn.classList.add('active');
    activeFilter = type;
  }
  renderCats();
}
function renderCats() {
  const search = document.getElementById('searchInput')?.value.toLowerCase() || '';
  let filtered = [...cats];
  if (activeFilter === 'domestik') filtered = filtered.filter(c => c.jenis === 'domestik');
  else if (activeFilter === 'langka') filtered = filtered.filter(c => c.jenis === 'langka');
  else if (activeFilter === 'tersedia') filtered = filtered.filter(c => c.status === 'Tersedia');
  else if (activeFilter === 'diadopsi') filtered = filtered.filter(c => c.status === 'Diadopsi');
  if (search) filtered = filtered.filter(c => c.nama.toLowerCase().includes(search) || c.ras.toLowerCase().includes(search));

  const grid = document.getElementById('catGrid');
  if (!grid) return;
  if (filtered.length === 0) {
    grid.innerHTML = `<div class="empty-state" style="grid-column:1/-1"><span class="emoji">🐾</span><p>Tidak ada kucing yang ditemukan.</p></div>`;
    return;
  }
  grid.innerHTML = filtered.map(c => {
    const biaya = hitungBiaya(c.jenis, c.vaksin, 1, true);
    const isDiadopsi = c.status === 'Diadopsi';
    return `
    <div class="cat-card">
      <div class="cat-card-art ${c.jenis}">
        <span class="kandang-badge">Kandang ${c.kandang}</span>
        <span class="jenis-badge ${c.jenis}">${c.jenis === 'domestik' ? 'Domestik' : '⭐ Ras'}</span>
        <span style="font-size:5rem">${catFace(c.id)}</span>
      </div>
      <div class="cat-card-body">
        <div class="cat-card-name">${c.nama}</div>
        <div class="cat-card-ras">${c.ras}</div>
        <div class="cat-card-meta">
          <span class="meta-chip chip-usia">🕰 ${c.usia} bln</span>
          <span class="meta-chip chip-warna">🎨 ${c.warna}</span>
          <span class="meta-chip chip-status-${c.status.toLowerCase()}">${isDiadopsi ? '💙' : '✅'} ${c.status}</span>
          <span class="meta-chip ${c.vaksin ? 'chip-vaksin' : 'chip-novaksin'}">${c.vaksin ? '💉 Vaksin ✓' : '⚠️ Vaksin?'}</span>
          <span class="meta-chip chip-biaya">💰 Rp ${biaya.toLocaleString('id-ID')}/bln</span>
        </div>
        <div class="cat-card-actions">
          ${!isDiadopsi ? `<button class="btn btn-primary btn-sm" onclick="goAdopsi(${c.id})">🤝 Adopsi</button>` : `<span class="btn btn-outline btn-sm" style="flex:1;text-align:center;cursor:default;opacity:0.6">💙 Sudah Diadopsi</span>`}
          <button class="btn btn-outline btn-sm" onclick="openEdit(${c.id})">✏️</button>
          <button class="btn btn-outline btn-sm" onclick="hapusKucing(${c.id})" style="color:#C9393A;border-color:#F0AAAA">🗑️</button>
        </div>
      </div>
    </div>`;
  }).join('');
  updateStats();
}

function goAdopsi(id) {
  showSection('adopsi');
  document.querySelectorAll('.nav-tab').forEach(t => t.classList.remove('active'));
  document.querySelectorAll('.nav-tab')[2].classList.add('active');
  setTimeout(() => { document.getElementById('aKucing').value = id; }, 50);
}

function hapusKucing(id) {
  if (!confirm('Yakin hapus kucing ini?')) return;
  cats = cats.filter(c => c.id !== id);
  renderCats(); updateStats();
  showToast('🗑️ Kucing berhasil dihapus');
}

// ══════════════════════════════════════════════
//  TAMBAH KUCING
// ══════════════════════════════════════════════
function updateRasField() {
  const kat = document.getElementById('fKategori').value;
  document.getElementById('rasRow').style.display = kat === 'langka' ? 'grid' : 'none';
  updateBiayaPreview();
}
function updateBiayaPreview() {
  const kat = document.getElementById('fKategori')?.value;
  const vaksin = document.getElementById('fVaksin')?.value === 'true';
  const preview = document.getElementById('biayaPreview');
  if (!kat) { preview.style.display = 'none'; return; }
  const biaya = hitungBiaya(kat, vaksin, 1, true);
  document.getElementById('biayaNum').textContent = 'Rp ' + biaya.toLocaleString('id-ID');
  preview.style.display = 'block';
}
document.addEventListener('change', function(e) {
  if (e.target.id === 'fVaksin' || e.target.id === 'fKategori') updateBiayaPreview();
});

function clearError(id) {
  document.getElementById('e-' + id)?.classList.remove('show');
  document.getElementById(id)?.classList.remove('error');
}
function setError(id, msg) {
  const el = document.getElementById('e-' + id);
  const inp = document.getElementById(id);
  if (el) { if (msg) el.textContent = msg; el.classList.add('show'); }
  if (inp) inp.classList.add('error');
  return false;
}

function tambahKucing() {
  let valid = true;
  ['fNama','fWarna','fUsia','fKategori'].forEach(id => clearError(id));
  const nama = document.getElementById('fNama').value.trim();
  const warna = document.getElementById('fWarna').value.trim();
  const usia = parseInt(document.getElementById('fUsia').value);
  const kat = document.getElementById('fKategori').value;
  const vaksin = document.getElementById('fVaksin').value === 'true';
  if (!nama)     { setError('fNama',  'Nama tidak boleh kosong'); valid = false; }
  if (!warna)    { setError('fWarna', 'Warna tidak boleh kosong'); valid = false; }
  if (!usia || usia < 1) { setError('fUsia', 'Usia harus lebih dari 0 bulan'); valid = false; }
  if (!kat)      { setError('fKategori', 'Pilih kategori kucing'); valid = false; }
  if (!valid) return;
  const ras = kat === 'domestik' ? 'Domestik' : document.getElementById('fRas').value;
  cats.push({ id: idCounter++, nama, ras, usia, status: 'Tersedia', warna, jenis: kat, vaksin, kandang: kandangCounter++ });
  resetFormTambah();
  showToast('✅ Kucing ' + nama + ' berhasil ditambahkan!');
  showSection('kucing');
  document.querySelectorAll('.nav-tab').forEach((t,i) => t.classList.toggle('active', i === 0));
}

function resetFormTambah() {
  ['fNama','fWarna','fUsia'].forEach(id => { const el = document.getElementById(id); if(el) el.value = ''; });
  document.getElementById('fKategori').value = '';
  document.getElementById('fVaksin').value = 'true';
  document.getElementById('rasRow').style.display = 'none';
  document.getElementById('biayaPreview').style.display = 'none';
  ['fNama','fWarna','fUsia','fKategori'].forEach(id => clearError(id));
}

// ══════════════════════════════════════════════
//  EDIT MODAL
// ══════════════════════════════════════════════
function openEdit(id) {
  const c = cats.find(x => x.id === id);
  if (!c) return;
  document.getElementById('editId').value = id;
  document.getElementById('editNama').value = c.nama;
  document.getElementById('editUsia').value = c.usia;
  document.getElementById('editWarna').value = c.warna;
  document.getElementById('editModal').classList.add('open');
}
function closeModal() { document.getElementById('editModal').classList.remove('open'); }
function simpanEdit() {
  const id = parseInt(document.getElementById('editId').value);
  const c = cats.find(x => x.id === id);
  if (!c) return;
  const n = document.getElementById('editNama').value.trim();
  const u = parseInt(document.getElementById('editUsia').value);
  const w = document.getElementById('editWarna').value.trim();
  if (n) c.nama = n;
  if (u && u > 0) c.usia = u;
  if (w) c.warna = w;
  closeModal(); renderCats();
  showToast('✅ Data kucing berhasil diupdate');
}

// ══════════════════════════════════════════════
//  ADOPSI
// ══════════════════════════════════════════════
function populateAdopsiSelect() {
  const sel = document.getElementById('aKucing');
  const tersedia = cats.filter(c => c.status === 'Tersedia');
  sel.innerHTML = '<option value="">-- Pilih Kucing --</option>';
  tersedia.forEach(c => {
    sel.innerHTML += `<option value="${c.id}">${catFace(c.id)} ${c.nama} (ID:${c.id} | Kandang:${c.kandang} | ${c.ras})</option>`;
  });
}
function buatAdopsi() {
  ['aKucing','aNama','aHp','aTglAdopsi','aTglKeluar'].forEach(id => clearError(id));
  const kucingId = parseInt(document.getElementById('aKucing').value);
  const nama = document.getElementById('aNama').value.trim();
  const hp = document.getElementById('aHp').value.trim();
  const tglAdopsi = document.getElementById('aTglAdopsi').value;
  const tglKeluar = document.getElementById('aTglKeluar').value;
  let valid = true;
  if (!kucingId) { setError('aKucing', 'Pilih kucing terlebih dahulu'); valid = false; }
  if (!nama)     { setError('aNama',   'Nama pengadopsi tidak boleh kosong'); valid = false; }
  if (!hp.match(/^08[1-9][0-9]{7,12}$/)) { setError('aHp', 'Format: 08xxxxxxxxxx (10-14 digit)'); valid = false; }
  if (!tglAdopsi) { setError('aTglAdopsi', 'Masukkan tanggal adopsi'); valid = false; }
  if (!tglKeluar) { setError('aTglKeluar', 'Masukkan tanggal keluar'); valid = false; }
  if (valid && tglKeluar < tglAdopsi) { setError('aTglKeluar', 'Tanggal keluar tidak boleh sebelum tanggal adopsi'); valid = false; }
  if (!valid) return;
  const kucing = cats.find(c => c.id === kucingId);
  if (!kucing) return;
  kucing.status = 'Diadopsi';
  adopsiList.push({ id: adopsiCounter++, kucing: { ...kucing }, namaPengadopsi: nama, noHp: hp, tanggalAdopsi: tglAdopsi, tanggalKeluar: tglKeluar });
  // reset form
  document.getElementById('aKucing').value = '';
  document.getElementById('aNama').value = '';
  document.getElementById('aHp').value = '';
  document.getElementById('aTglAdopsi').value = '';
  document.getElementById('aTglKeluar').value = '';
  updateStats();
  showToast('🎉 Adopsi berhasil dicatat!');
  showSection('riwayat');
  document.querySelectorAll('.nav-tab').forEach((t,i) => t.classList.toggle('active', i === 3));
}

// ══════════════════════════════════════════════
//  RIWAYAT
// ══════════════════════════════════════════════
function renderAdopsi() {
  const el = document.getElementById('adopsiList');
  if (!el) return;
  if (adopsiList.length === 0) {
    el.innerHTML = `<div class="empty-state"><span class="emoji">📋</span><p>Belum ada data adopsi.</p></div>`;
    return;
  }
  el.innerHTML = adopsiList.map(a => `
    <div class="adopsi-card">
      <div class="adopsi-id">ID<span>${a.id}</span></div>
      <div class="adopsi-info">
        <h4>${a.namaPengadopsi}</h4>
        <p>📱 ${a.noHp} &nbsp;|&nbsp; 📅 ${a.tanggalAdopsi} → 🚪 ${a.tanggalKeluar}</p>
        <p>Kandang asal: ${a.kucing.kandang} &nbsp;|&nbsp; Ras: ${a.kucing.ras} &nbsp;|&nbsp; Warna: ${a.kucing.warna}</p>
      </div>
      <div class="adopsi-cat">
        <div class="cat-face">${catFace(a.kucing.id)}</div>
        <p>${a.kucing.nama}</p>
        <span class="meta-chip chip-status-diadopsi" style="display:inline-block;margin-top:4px;font-size:0.72rem">💙 Diadopsi</span>
      </div>
    </div>
  `).join('');
}

// ══════════════════════════════════════════════
//  STATS
// ══════════════════════════════════════════════
function updateStats() {
  document.getElementById('statTotal').textContent = cats.length;
  document.getElementById('statTersedia').textContent = cats.filter(c => c.status === 'Tersedia').length;
  document.getElementById('statAdopsi').textContent = adopsiList.length;
}

// ══════════════════════════════════════════════
//  TOAST
// ══════════════════════════════════════════════
let toastTimer;
function showToast(msg) {
  const t = document.getElementById('toast');
  t.textContent = msg;
  t.classList.add('show');
  clearTimeout(toastTimer);
  toastTimer = setTimeout(() => t.classList.remove('show'), 3000);
}

// ══════════════════════════════════════════════
//  BOOT
// ══════════════════════════════════════════════
initData();
// Set today's date as default for adopsi form
const today = new Date().toISOString().split('T')[0];
document.getElementById('aTglAdopsi').value = today;
document.getElementById('aTglKeluar').value = today;
</script>
</body>
</html>
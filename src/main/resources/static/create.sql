-- 创建月份表
CREATE TABLE months (
    id INT PRIMARY KEY AUTO_INCREMENT,
    month_name VARCHAR(20) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 创建旅游推荐表
CREATE TABLE travel_recommendations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    month_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    image_url VARCHAR(500),
    description TEXT,
    tags JSON,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (month_id) REFERENCES months(id) ON DELETE CASCADE,
    INDEX idx_month_id (month_id),
    INDEX idx_created_at (created_at)
);

-- 插入月份数据
INSERT INTO months (month_name) VALUES
('一月'), ('二月'), ('三月'), ('四月'), ('五月'),
('六月'), ('七月'), ('八月'), ('九月'), ('十月'),
('十一月'), ('十二月');

-- 插入旅游推荐数据（5条示例）
INSERT INTO travel_recommendations (month_id, name, image_url, description, tags) VALUES
(1, '哈尔滨冰雪大世界', 'https://images.unsplash.com/photo-1558029062-a37889b87526?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80', '体验世界最大的冰雪艺术景观', '["冰雪", "节庆", "东北"]'),
(1, '三亚亚龙湾', 'https://images.unsplash.com/photo-1508057198894-247b23fe5ade?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80', '冬日避寒的绝佳选择', '["海滩", "温暖", "海岛"]'),
(2, '丽江古城', 'https://example.com/lijiang.jpg', '春节期间的古城年味十足', '["古城", "春节", "云南"]'),
(2, '厦门鼓浪屿', 'https://example.com/gulangyu.jpg', '温暖的南方小岛，春节度假好去处', '["海岛", "文艺", "福建"]'),
(3, '婺源油菜花', 'https://example.com/wuyuan.jpg', '金色花海与徽派建筑的完美结合', '["赏花", "摄影", "江西"]');


-- 查询一月的旅游推荐
SELECT m.month_name, tr.name, tr.description, tr.tags
FROM travel_recommendations tr
JOIN months m ON tr.month_id = m.id
WHERE m.month_name = '一月';

-- 查询所有推荐并按月份排序
SELECT m.month_name, tr.name, tr.description
FROM travel_recommendations tr
JOIN months m ON tr.month_id = m.id
ORDER BY m.id;
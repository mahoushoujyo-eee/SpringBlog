        user.setEncryptedPassword(passwordEncoder.encode(user.getEncryptedPassword()));
        user.setCreatorId(1L); // 设置默认 creator_id
        userMapper.insertUser(user);